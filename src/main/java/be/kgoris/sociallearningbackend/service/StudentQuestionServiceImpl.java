package be.kgoris.sociallearningbackend.service;

import be.kgoris.sociallearningbackend.allenum.QuestionType;
import be.kgoris.sociallearningbackend.dao.AnswerRepository;
import be.kgoris.sociallearningbackend.dao.QuestionnaireRepository;
import be.kgoris.sociallearningbackend.dao.StudentQuestionRepository;
import be.kgoris.sociallearningbackend.dao.StudentRepository;
import be.kgoris.sociallearningbackend.dto.*;
import be.kgoris.sociallearningbackend.entities.*;
import be.kgoris.sociallearningbackend.mapper.QuestionnaireMapper;
import be.kgoris.sociallearningbackend.mapper.StudentMapper;
import be.kgoris.sociallearningbackend.mapper.StudentQuestionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentQuestionServiceImpl implements StudentQuestionService{

    private final StudentQuestionMapper studentQuestionMapper;
    private final StudentMapper studentMapper;
    private final StudentQuestionRepository studentQuestionRepository;
    private final QuestionnaireRepository questionnaireRepository;
    private final StudentRepository studentRepository;
    private final QuestionnaireMapper questionnaireMapper;
    private final AnswerRepository answerRepository;

    @Override
    public List<StudentQuestionDto> findByStudent(StudentDto studentDto) {
        Map<Questionnaire, List<StudentQuestion>> studentQuestionsMap = new HashMap<>();
        List<StudentQuestion> studentQuestions = studentQuestionRepository.findAllByStudent(
                                                                            studentMapper.fromDtoToModel(studentDto));
        //go through all the student question and separate them by questionnaire
        for(StudentQuestion studentQuestion : studentQuestions){
            List<StudentQuestion> stList = studentQuestionsMap.get(studentQuestion.getQuestion().getQuestionnaire());
            if(stList == null){
                studentQuestionsMap.put(studentQuestion.getQuestion().getQuestionnaire(), new ArrayList<>());
                stList = studentQuestionsMap.get(studentQuestion.getQuestion().getQuestionnaire());
            }
            if(!studentQuestion.isLocked()) {
                stList.add(studentQuestion);
            }
            //sort the student question list by question sequence number
            if(stList.size()>1){
                stList.sort(new Comparator<StudentQuestion>() {
                        @Override
                        public int compare(StudentQuestion x, StudentQuestion y) {
                            return x.getQuestion().getSequenceNumber().compareTo(y.getQuestion().getSequenceNumber());
                        }
                    });
                }
            }

        //for each questionnaire, get the last student question, it will become the "current" one
        List<StudentQuestion> finalStudentQuestions = new ArrayList<>();
        for (Map.Entry<Questionnaire, List<StudentQuestion>> pair : studentQuestionsMap.entrySet()) {
            if(org.apache.commons.collections4.CollectionUtils.isNotEmpty(pair.getValue())) {
                finalStudentQuestions.add(pair.getValue().get(pair.getValue().size() - 1));
            }
        }

        if(!CollectionUtils.isEmpty(studentQuestions)){
            return finalStudentQuestions.stream()
                                    .map(this::mapStudentQuestionToStudentQuestionDto)
                                    .collect(Collectors.toList());
        }
        return null;
    }
    private StudentQuestionDto mapStudentQuestionToStudentQuestionDto(StudentQuestion studentQuestion){
        StudentQuestionDto studentQuestionDto = studentQuestionMapper.fromModelToDto(studentQuestion);
        this.fillInStudentQuestionDtoWithQuestionnaireDto(studentQuestionDto, studentQuestion.getQuestion().getQuestionnaire());
        return studentQuestionDto;
    }
    private void fillInStudentQuestionDtoWithQuestionnaireDto(StudentQuestionDto studentQuestionDto, Questionnaire questionnaire){
        QuestionnaireDto questionnaireDto = questionnaireMapper.fromModelToDto(questionnaire);
        studentQuestionDto.setQuestionnaire(questionnaireDto);
    }

    @Override
    public StudentQuestionDto findById(Integer id) {
        Optional<StudentQuestion> studentQuestion = studentQuestionRepository.findById(id);
        Optional<StudentQuestionDto> studentQuestionDtoOpt = studentQuestion.map(studentQuestionMapper::fromModelToDto);
        if(studentQuestionDtoOpt.isPresent()){
            StudentQuestionDto studentQuestionDto = studentQuestionDtoOpt.get();
            this.fillInStudentQuestionDtoWithQuestionnaireDto(studentQuestionDto, studentQuestion.get().getQuestion().getQuestionnaire());
            return studentQuestionDto;
        }
        return null;
    }

    @Override
    public StudentQuestionDto createByQuestionnaireIdAndStudentDto(Integer questionnaireId, StudentDto studentDto) {
        Questionnaire questionnaire = questionnaireRepository.findById(questionnaireId).orElse(null);
        Student student = studentRepository.findById(studentDto.getId()).orElse(null);
        StudentQuestion studentQuestion = StudentQuestion.builder()
                .question(questionnaire.getQuestions()
                                        .stream()
                                        .filter(question -> question.getSequenceNumber() == 1)
                                        .findFirst().orElse(null))
                .student(student)
                .locked(false)
                .build();
        studentQuestion = studentQuestionRepository.save(studentQuestion);
        StudentQuestionDto studentQuestionDto = studentQuestionMapper.fromModelToDto(studentQuestion);
        fillInStudentQuestionDtoWithQuestionnaireDto(studentQuestionDto, questionnaire);
        return studentQuestionDto;
    }

    private Question getNextQuestion(StudentQuestion studentQuestion){
        return studentQuestion.getQuestion()
                .getQuestionnaire()
                .getQuestions()
                .stream()
                .filter(q -> q.getSequenceNumber().equals(studentQuestion.getQuestion().getSequenceNumber() + 1))
                .findFirst().orElse(null);
    }

    private Question getPreviousQuestion(StudentQuestion studentQuestion){
        return  studentQuestion.getQuestion()
                .getQuestionnaire()
                .getQuestions()
                .stream()
                .filter(q -> q.getSequenceNumber().equals(studentQuestion.getQuestion().getSequenceNumber() - 1))
                .findFirst().orElse(null);
    }

    private void fillInStudentQuestionWithQuestionnaire(StudentQuestion studentQuestion){
        Questionnaire questionnaire = questionnaireRepository.findByQuestionId(studentQuestion.getQuestion().getId());
        studentQuestion.getQuestion().setQuestionnaire(questionnaire);
    }
    @Override
    public StudentQuestionDto next(StudentQuestionDto studentQuestionDto) {
        StudentQuestion studentQuestion = studentQuestionMapper.fromDtoToModel(studentQuestionDto);
        fillInStudentQuestionWithQuestionnaire(studentQuestion);
        studentQuestion = studentQuestionRepository.save(studentQuestion);
        if(studentQuestion.getQuestion().getSequenceNumber() < studentQuestion.getQuestion().getQuestionnaire().getQuestions().size()){
            Question nextQuestion = getNextQuestion(studentQuestion);
            StudentQuestion nextStudentQuestion = studentQuestionRepository.findStudentQuestionByQuestionAndStudent(nextQuestion, studentQuestion.getStudent());
            if(nextStudentQuestion == null) {
                nextStudentQuestion = StudentQuestion.builder()
                        .question(nextQuestion)
                        .student(studentQuestion.getStudent())
                        .build();
                nextStudentQuestion = studentQuestionRepository.save(nextStudentQuestion);
            }
            return this.mapStudentQuestionToStudentQuestionDto(nextStudentQuestion);
        }
        return studentQuestionDto;
    }

    @Override
    public StudentQuestionDto previous(StudentQuestionDto studentQuestionDto) {
        StudentQuestion studentQuestion = studentQuestionMapper.fromDtoToModel(studentQuestionDto);
        studentQuestion = studentQuestionRepository.save(studentQuestion);
        if(studentQuestion.getQuestion().getSequenceNumber() > 1){
            Question previousQuestion = getPreviousQuestion(studentQuestion);
            StudentQuestion previousStudentQuestion = studentQuestionRepository.findStudentQuestionByQuestionAndStudent(previousQuestion, studentQuestion.getStudent());
            return studentQuestionMapper.fromModelToDto(previousStudentQuestion);
        }
        return studentQuestionDto;
    }

    @Override
    public void save(StudentQuestionDto studentQuestionDto) {
        StudentQuestion studentQuestion = studentQuestionMapper.fromDtoToModel(studentQuestionDto);
        studentQuestionRepository.save(studentQuestion);
    }

    @Override
    public void lock(Integer questionnaireId, StudentDto studentDto) {
        Optional<Questionnaire> questionnaire = questionnaireRepository.findById(questionnaireId);
        Student student = studentMapper.fromDtoToModel(studentDto);
        if(questionnaire.isPresent()){
            List<StudentQuestion> studentQuestions = studentQuestionRepository.findByQuestionQuestionnaireAndStudent(questionnaire.get(), student);
            studentQuestions.forEach(studentQuestion -> {
                studentQuestion.setLocked(true);
                studentQuestionRepository.save(studentQuestion);
            });
        }
    }

    public ResultsDto getResultsForStudentAndQuestionnaire(StudentDto studentDto, Integer questionnaireId){
        Optional<Questionnaire> questionnaire = questionnaireRepository.findById(questionnaireId);
        Student student = studentMapper.fromDtoToModel(studentDto);
        if(questionnaire.isPresent()){
            List<StudentQuestion> studentQuestions = studentQuestionRepository.findByQuestionQuestionnaireAndStudent(questionnaire.get(), student);
            return this.buildResults(questionnaire.get(), studentQuestions);
        }
        return null;
    }

    @Override
    public StudentQuestionDto reset(StudentDto studentDto, Integer questionnaireId) {
        Optional<Questionnaire> questionnaire = questionnaireRepository.findById(questionnaireId);
        Student student = studentMapper.fromDtoToModel(studentDto);
        if(questionnaire.isPresent()){
            List<StudentQuestion> studentQuestions = studentQuestionRepository.findByQuestionQuestionnaireAndStudent(questionnaire.get(), student);
            studentQuestions.forEach(studentQuestion -> {
                studentQuestion.setLocked(false);
                Answer answer = studentQuestion.getAnswer();
                studentQuestion.setAnswer(null);
                studentQuestionRepository.save(studentQuestion);
                answerRepository.delete(answer);
            });
            return studentQuestionMapper.fromModelToDto(studentQuestions
                                                            .stream()
                                                            .filter(studentQuestion -> studentQuestion.getQuestion().getSequenceNumber().equals(1))
                                                            .findFirst().orElse(null));
        }
        return null;
    }

    @Override
    public StudentQuestionDto visit(StudentDto studentDto, Integer questionnaireId){
        Optional<Questionnaire> questionnaire = questionnaireRepository.findById(questionnaireId);
        Student student = studentMapper.fromDtoToModel(studentDto);
        if(questionnaire.isPresent()){
            List<StudentQuestion> studentQuestions = studentQuestionRepository.findByQuestionQuestionnaireAndStudent(questionnaire.get(), student);
            return studentQuestionMapper.fromModelToDto(studentQuestions
                    .stream()
                    .filter(studentQuestion -> studentQuestion.getQuestion().getSequenceNumber().equals(1))
                    .findFirst().orElse(null));
        }
        return null;
    }

    private ResultsDto buildResults(Questionnaire questionnaire, List<StudentQuestion> studentQuestions){
        ResultsDto resultsDto = new ResultsDto(questionnaireMapper.fromModelToDto(questionnaire));

        for(StudentQuestion studentQuestion : studentQuestions){
            Question question = studentQuestion.getQuestion();
            OfficialAnswer officialAnswer = question.getOfficialAnswer();
            Answer studentAnswer = studentQuestion.getAnswer();
            if(QuestionType.FREE_TEXT.equals(question.getType())){
                checkFreeTextOrTrueFalseAnswer(resultsDto, question, officialAnswer, studentAnswer);
            }else if(QuestionType.MULTIPLE_CHOICE.equals(question.getType())){
                checkMultipleChoiceAnswer(resultsDto, question, officialAnswer, studentAnswer);
            }else if(QuestionType.SINGLE_CHOICE.equals(question.getType())){

            }else if(QuestionType.TRUE_FALSE.equals(question.getType())){
                checkFreeTextOrTrueFalseAnswer(resultsDto, question, officialAnswer, studentAnswer);
            }
        }
        return  resultsDto;
    }

    private void checkMultipleChoiceAnswer(ResultsDto resultsDto, Question question, OfficialAnswer officialAnswer, Answer studentAnswer) {
        if(!officialAnswer.getPropositions().get(0).equals(studentAnswer.getProposition().getValue())){
            ErrorDto errorDto = ErrorDto.builder()
                    .questionTitle(question.getTitle())
                    .userAnswer(studentAnswer.getProposition().getValue())
                    .trueAnswer(officialAnswer.getPropositions().get(0).getValue())
                    .build();
            resultsDto.addError(errorDto);
        }
    }

    private void checkFreeTextOrTrueFalseAnswer(ResultsDto resultsDto, Question question, OfficialAnswer officialAnswer, Answer studentAnswer) {
        if(!StringUtils.hasText(studentAnswer.getValue()) || !officialAnswer.getValue().toLowerCase().equals(studentAnswer.getValue().toLowerCase())) {
            ErrorDto errorDto = ErrorDto.builder()
                    .questionTitle(question.getTitle())
                    .userAnswer(studentAnswer.getValue())
                    .trueAnswer(officialAnswer.getValue())
                    .build();
            resultsDto.addError(errorDto);
        }
    }

}

package be.kgoris.sociallearningbackend.service;

import be.kgoris.sociallearningbackend.dao.QuestionnaireRepository;
import be.kgoris.sociallearningbackend.dao.StudentQuestionRepository;
import be.kgoris.sociallearningbackend.dao.StudentRepository;
import be.kgoris.sociallearningbackend.dto.QuestionnaireDto;
import be.kgoris.sociallearningbackend.dto.StudentDto;
import be.kgoris.sociallearningbackend.dto.StudentQuestionDto;
import be.kgoris.sociallearningbackend.entities.Question;
import be.kgoris.sociallearningbackend.entities.Questionnaire;
import be.kgoris.sociallearningbackend.entities.Student;
import be.kgoris.sociallearningbackend.entities.StudentQuestion;
import be.kgoris.sociallearningbackend.mapper.QuestionnaireMapper;
import be.kgoris.sociallearningbackend.mapper.StudentMapper;
import be.kgoris.sociallearningbackend.mapper.StudentQuestionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
            stList.add(studentQuestion);
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
            finalStudentQuestions.add(pair.getValue().get(pair.getValue().size() - 1));
        }

        if(!CollectionUtils.isEmpty(studentQuestions)){
            return studentQuestions.stream()
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
            StudentQuestion nextStudentQuestion = StudentQuestion.builder()
                    .question(nextQuestion)
                    .student(studentQuestion.getStudent())
                    .build();
            nextStudentQuestion = studentQuestionRepository.save(nextStudentQuestion);
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
            studentQuestionRepository.findStudentQuestionByQuestionAndStudent(previousQuestion, studentQuestion.getStudent());
            StudentQuestion nextStudentQuestion = StudentQuestion.builder()
                    .question(previousQuestion)
                    .student(studentQuestion.getStudent())
                    .build();
            nextStudentQuestion = studentQuestionRepository.save(nextStudentQuestion);
            return studentQuestionMapper.fromModelToDto(nextStudentQuestion);
        }
        return studentQuestionDto;
    }
}

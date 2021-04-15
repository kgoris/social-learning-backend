package be.kgoris.sociallearningbackend.service;

import be.kgoris.sociallearningbackend.dao.QuestionnaireRepository;
import be.kgoris.sociallearningbackend.dao.StudentQuestionRepository;
import be.kgoris.sociallearningbackend.dao.StudentRepository;
import be.kgoris.sociallearningbackend.dto.QuestionnaireDto;
import be.kgoris.sociallearningbackend.dto.StudentDto;
import be.kgoris.sociallearningbackend.dto.StudentQuestionDto;
import be.kgoris.sociallearningbackend.entities.Questionnaire;
import be.kgoris.sociallearningbackend.entities.Student;
import be.kgoris.sociallearningbackend.entities.StudentQuestion;
import be.kgoris.sociallearningbackend.mapper.QuestionnaireMapper;
import be.kgoris.sociallearningbackend.mapper.StudentMapper;
import be.kgoris.sociallearningbackend.mapper.StudentQuestionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
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
        List<StudentQuestion> studentQuestions = studentQuestionRepository.findAllByStudent(
                                                                            studentMapper.fromDtoToModel(studentDto));
        if(!CollectionUtils.isEmpty(studentQuestions)){
            return studentQuestions.stream()
                                    .map(studentQuestion -> {
                                        StudentQuestionDto studentQuestionDto = studentQuestionMapper.fromModelToDto(studentQuestion);
                                        this.fillInStudentQuestionDtoWithQuestionnaireDto(studentQuestionDto, studentQuestion.getQuestion().getQuestionnaire());
                                        return studentQuestionDto;
                                    })
                                    .collect(Collectors.toList());
        }
        return null;
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
}

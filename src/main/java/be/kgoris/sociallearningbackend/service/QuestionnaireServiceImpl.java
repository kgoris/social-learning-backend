package be.kgoris.sociallearningbackend.service;

import be.kgoris.sociallearningbackend.allenum.AccessType;
import be.kgoris.sociallearningbackend.dao.QuestionnaireRepository;
import be.kgoris.sociallearningbackend.dao.StudentQuestionRepository;
import be.kgoris.sociallearningbackend.dao.StudentRepository;
import be.kgoris.sociallearningbackend.dto.QuestionnaireDto;
import be.kgoris.sociallearningbackend.dto.StudentDto;
import be.kgoris.sociallearningbackend.entities.Questionnaire;
import be.kgoris.sociallearningbackend.entities.Student;
import be.kgoris.sociallearningbackend.entities.StudentQuestion;
import be.kgoris.sociallearningbackend.mapper.QuestionnaireMapper;
import be.kgoris.sociallearningbackend.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionnaireServiceImpl implements QuestionnaireService {
    private final StudentMapper studentMapper;
    private final QuestionnaireRepository questionnaireRepository;
    private final QuestionnaireMapper questionnaireMapper;
    private final StudentQuestionRepository studentQuestionRepository;
    private final StudentRepository studentRepository;


    @Override
    public List<QuestionnaireDto> getQuestionnairesByStudentAndAccessType(String studentUsername, AccessType accessType) {
        List<Questionnaire> questionnaires = questionnaireRepository.findAllByStudentAndAccessType(studentUsername, accessType);
        return questionnaires.stream().map(questionnaireMapper::fromModelToDto).collect(Collectors.toList());
    }

    @Override
    public List<QuestionnaireDto> getQuestionnairesLockedByStudentAndAccessType(String studentUsername, AccessType accessType) {
        Optional<Student> student = this.studentRepository.findByUsername(studentUsername);
        if(student.isPresent()) {
            List<Questionnaire> questionnaires = questionnaireRepository.findAllByStudentAndAccessType(studentUsername, accessType);
            List<Questionnaire> questionnaireFiltered = questionnaires.stream()
                    .filter(q -> {
                                return studentQuestionRepository.findByQuestionQuestionnaireAndStudent(q, student.get())
                                        .stream().anyMatch(StudentQuestion::isLocked);
                            }
                    )
                    .collect(Collectors.toList());
            return questionnaireFiltered.stream().map(this.questionnaireMapper::fromModelToDto).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public QuestionnaireDto findById(Integer id) {
        Optional<Questionnaire> questionnaire = questionnaireRepository.findById(id);
        return questionnaire.map(questionnaireMapper::fromModelToDto).orElse(null);
    }

}

package be.kgoris.sociallearningbackend.service;

import be.kgoris.sociallearningbackend.allenum.AccessType;
import be.kgoris.sociallearningbackend.dao.QuestionnaireDao;
import be.kgoris.sociallearningbackend.dto.QuestionnaireDto;
import be.kgoris.sociallearningbackend.dto.StudentDto;
import be.kgoris.sociallearningbackend.entities.Questionnaire;
import be.kgoris.sociallearningbackend.entities.Student;
import be.kgoris.sociallearningbackend.mapper.QuestionnaireMapper;
import be.kgoris.sociallearningbackend.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionnaireServiceImpl implements QuestionnaireService {
    private final StudentMapper studentMapper;
    private final QuestionnaireDao questionnaireDao;
    private final QuestionnaireMapper questionnaireMapper;


    @Override
    public List<QuestionnaireDto> getQuestionnairesByStudentAndAccessType(StudentDto studentDto, AccessType accessType) {
        Student student = studentMapper.fromDtoToModel(studentDto);
        List<Questionnaire> questionnaires = questionnaireDao.findAllByStudentAndAccessType(student.getEmail(), accessType);
        return questionnaires.stream().map(questionnaireMapper::fromModelToDto).collect(Collectors.toList());
    }
}

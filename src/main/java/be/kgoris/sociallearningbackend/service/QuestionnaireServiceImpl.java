package be.kgoris.sociallearningbackend.service;

import be.kgoris.sociallearningbackend.allenum.AccessType;
import be.kgoris.sociallearningbackend.dao.QuestionnaireDao;
import be.kgoris.sociallearningbackend.dto.QuestionnaireDto;
import be.kgoris.sociallearningbackend.dto.StudentDto;
import be.kgoris.sociallearningbackend.entities.Questionnaire;
import be.kgoris.sociallearningbackend.entities.Student;
import be.kgoris.sociallearningbackend.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionnaireServiceImpl implements QuestionnaireService {
    private final StudentMapper studentMapper;
    private final QuestionnaireDao questionnaireDao;

    @Override
    public List<QuestionnaireDto> getWorkQuestionnairesDtoByStudent(StudentDto studentDto) {
        Student student = studentMapper.
        return questionnaireDao.findAllByStudentAndAccessType(st);
    }

    @Override
    public List<QuestionnaireDto> getObserveQuestionnairesDtoByStudent(StudentDto studentDto) {
        return null;
    }

    @Override
    public List<Questionnaire> getQuestionnairesByStudentAndAccessType(Student student, AccessType accessType) {
        return null;
    }
}

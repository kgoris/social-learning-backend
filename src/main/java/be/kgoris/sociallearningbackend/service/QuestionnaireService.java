package be.kgoris.sociallearningbackend.service;

import be.kgoris.sociallearningbackend.allenum.AccessType;
import be.kgoris.sociallearningbackend.dto.QuestionnaireDto;
import be.kgoris.sociallearningbackend.dto.StudentDto;
import be.kgoris.sociallearningbackend.entities.Questionnaire;
import be.kgoris.sociallearningbackend.entities.Student;

import java.util.List;

public interface QuestionnaireService {
    List<QuestionnaireDto> getQuestionnairesByStudentAndAccessType(StudentDto studentDto, AccessType accessType);
}

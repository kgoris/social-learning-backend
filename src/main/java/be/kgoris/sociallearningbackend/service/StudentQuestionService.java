package be.kgoris.sociallearningbackend.service;

import be.kgoris.sociallearningbackend.dto.StudentDto;
import be.kgoris.sociallearningbackend.dto.StudentQuestionDto;
import be.kgoris.sociallearningbackend.entities.StudentQuestion;

import java.util.List;


public interface StudentQuestionService {
    List<StudentQuestionDto> findByStudent(StudentDto studentDto);
    StudentQuestionDto findById(Integer id);
    StudentQuestionDto createByQuestionnaireIdAndStudentDto(Integer questionnaireId, StudentDto studentDto);
}

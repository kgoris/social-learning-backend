package be.kgoris.sociallearningbackend.service;

import be.kgoris.sociallearningbackend.dto.ResultsDto;
import be.kgoris.sociallearningbackend.dto.StudentDto;
import be.kgoris.sociallearningbackend.dto.StudentQuestionDto;
import be.kgoris.sociallearningbackend.entities.Student;
import be.kgoris.sociallearningbackend.entities.StudentQuestion;

import java.util.List;


public interface StudentQuestionService {
    List<StudentQuestionDto> findByStudent(StudentDto studentDto);
    StudentQuestionDto findById(Integer id);
    StudentQuestionDto createByQuestionnaireIdAndStudentDto(Integer questionnaireId, StudentDto studentDto);
    StudentQuestionDto next(StudentQuestionDto studentQuestionDto);
    StudentQuestionDto previous(StudentQuestionDto studentQuestionDto);
    void lock(Integer questionnaireId, StudentDto student);
    ResultsDto getResultsForStudentAndQuestionnaire(StudentDto studentDto, Integer questionnaireId);
    StudentQuestionDto reset(StudentDto studentDto, Integer questionnaireId);
}

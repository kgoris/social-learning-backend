package be.kgoris.sociallearningbackend.service;

import be.kgoris.sociallearningbackend.dto.ResultsDto;
import be.kgoris.sociallearningbackend.dto.StudentDto;
import be.kgoris.sociallearningbackend.dto.StudentQuestionDto;

import java.util.List;


public interface StudentQuestionService {
    List<StudentQuestionDto> findByStudent(String studentUsername);
    StudentQuestionDto findById(Integer id);
    StudentQuestionDto createByQuestionnaireIdAndStudentDto(Integer questionnaireId, String studentUsername);
    StudentQuestionDto next(StudentQuestionDto studentQuestionDto);
    StudentQuestionDto previous(StudentQuestionDto studentQuestionDto);
    void save(StudentQuestionDto studentQuestionDto);
    void lock(Integer questionnaireId, String studentUsername);
    ResultsDto getResultsForStudentAndQuestionnaire(StudentDto studentDto, Integer questionnaireId);
    StudentQuestionDto reset(String studentUsername, Integer questionnaireId);
    StudentQuestionDto visit(String studentUsername, Integer questionnaireId);
}

package be.kgoris.sociallearningbackend.mapper;

import be.kgoris.sociallearningbackend.dto.StudentQuestionDto;
import be.kgoris.sociallearningbackend.entities.StudentQuestion;

public interface StudentQuestionMapper {
    StudentQuestion fromDtoToModel(StudentQuestionDto studentQuestionDto);
    StudentQuestionDto fromModelToDto(StudentQuestion studentQuestion);
}

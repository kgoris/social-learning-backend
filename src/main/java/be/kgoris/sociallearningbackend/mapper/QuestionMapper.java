package be.kgoris.sociallearningbackend.mapper;

import be.kgoris.sociallearningbackend.dto.QuestionDto;
import be.kgoris.sociallearningbackend.entities.Question;

public interface QuestionMapper {
    Question fromDtoToModel(QuestionDto questionDto);
    QuestionDto fromModelToDto(Question question);
}

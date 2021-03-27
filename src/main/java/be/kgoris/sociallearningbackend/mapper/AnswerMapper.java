package be.kgoris.sociallearningbackend.mapper;

import be.kgoris.sociallearningbackend.dto.AnswerDto;
import be.kgoris.sociallearningbackend.entities.Answer;

public interface AnswerMapper {
    Answer fromDtoToModel(AnswerDto answerDto);
    AnswerDto fromModelToDto(Answer answer);
}

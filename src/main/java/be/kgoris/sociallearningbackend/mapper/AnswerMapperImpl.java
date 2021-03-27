package be.kgoris.sociallearningbackend.mapper;

import be.kgoris.sociallearningbackend.dto.AnswerDto;
import be.kgoris.sociallearningbackend.entities.Answer;
import org.springframework.stereotype.Service;

@Service
public class AnswerMapperImpl implements AnswerMapper{
    @Override
    public Answer fromDtoToModel(AnswerDto answerDto) {
        return null;
    }

    @Override
    public AnswerDto fromModelToDto(Answer answer) {
        return null;
    }
}

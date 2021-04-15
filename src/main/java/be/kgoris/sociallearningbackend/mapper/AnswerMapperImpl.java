package be.kgoris.sociallearningbackend.mapper;

import be.kgoris.sociallearningbackend.dto.AnswerDto;
import be.kgoris.sociallearningbackend.entities.Answer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerMapperImpl implements AnswerMapper{

    private final PropositionMapper propositionMapper;

    @Override
    public Answer fromDtoToModel(AnswerDto answerDto) {
        return Answer.builder()
                .id(answerDto.getId())
                .proposition(answerDto.getProposition() != null
                        ? propositionMapper.fromDtoToModel(answerDto.getProposition())
                        : null)
                .value(answerDto.getValue())
                .build();
    }

    @Override
    public AnswerDto fromModelToDto(Answer answer) {
        return AnswerDto.builder()
                .id(answer.getId())
                .proposition(answer.getProposition() != null
                        ? propositionMapper.fromModelToDto(answer.getProposition())
                        : null)
                .value(answer.getValue())
                .build();
    }
}

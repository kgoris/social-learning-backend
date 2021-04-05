package be.kgoris.sociallearningbackend.mapper;

import be.kgoris.sociallearningbackend.dto.QuestionDto;
import be.kgoris.sociallearningbackend.entities.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionMapperImpl implements QuestionMapper {
    private final OfficialAnswerMapper officialAnswerMapper;
    private final PropositionMapper propositionMapper;

    @Override
    public Question fromDtoToModel(QuestionDto questionDto) {
        return null;
    }

    @Override
    public QuestionDto fromModelToDto(Question question) {
        return QuestionDto.builder()
                .id(question.getId())
                .officialAnswer(officialAnswerMapper.fromModelToDto(question.getOfficialAnswer()))
                .propositions(question.getPropositions()
                                        .stream()
                                        .map(propositionMapper::fromModelToDto)
                                        .collect(Collectors.toList()))
                .questionnaireId(question.getQuestionnaire().getId())
                .sequenceNumber(question.getSequenceNumber())
                .title(question.getTitle())
                .type(question.getType())
                .build();
    }
}

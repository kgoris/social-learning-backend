package be.kgoris.sociallearningbackend.mapper;

import be.kgoris.sociallearningbackend.dto.QuestionDto;
import be.kgoris.sociallearningbackend.dto.QuestionnaireDto;
import be.kgoris.sociallearningbackend.entities.Question;
import be.kgoris.sociallearningbackend.entities.Questionnaire;
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
                .officialAnswer(question.getOfficialAnswer() != null
                        ? officialAnswerMapper.fromModelToDto(question.getOfficialAnswer())
                        : null)
                .propositions(question.getPropositions() != null
                        ? question.getPropositions()
                                        .stream()
                                        .map(propositionMapper::fromModelToDto)
                                        .collect(Collectors.toList())
                        : null)
                .questionnaireId(question.getQuestionnaire() != null
                        ? question.getQuestionnaire().getId()
                        : null)
                .sequenceNumber(question.getSequenceNumber())
                .title(question.getTitle())
                .type(question.getType())
                .build();
    }

    @Override
    public QuestionDto fromModelToDto(Question question, QuestionnaireDto questionnaireDto) {
        QuestionDto questionDto = this.fromModelToDto(question);
        questionDto.setQuestionnaire(questionnaireDto);
        return questionDto;
    }
}

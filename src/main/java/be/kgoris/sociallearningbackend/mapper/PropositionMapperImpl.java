package be.kgoris.sociallearningbackend.mapper;

import be.kgoris.sociallearningbackend.dto.PropositionDto;
import be.kgoris.sociallearningbackend.entities.OfficialAnswer;
import be.kgoris.sociallearningbackend.entities.Proposition;
import be.kgoris.sociallearningbackend.entities.Question;
import org.springframework.stereotype.Service;

@Service
public class PropositionMapperImpl implements PropositionMapper {
    @Override
    public Proposition fromDtoToModel(PropositionDto propositionDto) {
        return Proposition.builder()
                .id(propositionDto.getId())
                .build();
    }

    @Override
    public PropositionDto fromModelToDto(Proposition proposition) {
        OfficialAnswer officialAnswer = proposition.getOfficialAnswer();
        Question question = proposition.getQuestion();

        return PropositionDto.builder()
                .id(proposition.getId())
                .officialAnswerId(officialAnswer != null ? officialAnswer.getId() : null)
                .questionId(question != null ? question.getId().toString() : null)
                .value(proposition.getValue())
                .build();
    }
}

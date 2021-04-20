package be.kgoris.sociallearningbackend.mapper;

import be.kgoris.sociallearningbackend.dto.OfficialAnswerDto;
import be.kgoris.sociallearningbackend.entities.OfficialAnswer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfficialAnswerMapperImpl implements OfficialAnswerMapper {

    private final PropositionMapper propositionMapper;

    @Override
    public OfficialAnswer fromDtoToModel(OfficialAnswerDto officialAnswerDto) {
        return OfficialAnswer.builder()
                .id(officialAnswerDto.getId())
                .propositions(!officialAnswerDto.getPropositionDtos().isEmpty()
                        ? officialAnswerDto.getPropositionDtos()
                                .stream()
                                .map(propositionMapper::fromDtoToModel)
                                .collect(Collectors.toList())
                        : null)
                .value(officialAnswerDto.getValue())
                .build();
    }

    @Override
    public OfficialAnswerDto fromModelToDto(OfficialAnswer officialAnswer) {
        return OfficialAnswerDto.builder()
                .id(officialAnswer.getId())
                .value(officialAnswer.getValue())
                .propositionDtos(officialAnswer.getPropositions() != null
                        ? officialAnswer.getPropositions()
                                                    .stream()
                                                    .map(propositionMapper::fromModelToDto)
                                                    .collect(Collectors.toList())
                        : null)
                .build();
    }
}

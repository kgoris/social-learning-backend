package be.kgoris.sociallearningbackend.mapper;

import be.kgoris.sociallearningbackend.dto.PropositionDto;
import be.kgoris.sociallearningbackend.entities.Proposition;

public interface PropositionMapper {
    Proposition fromDtoToModel(PropositionDto propositionDto);
    PropositionDto fromModelToDto(Proposition proposition);
}

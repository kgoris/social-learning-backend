package be.kgoris.sociallearningbackend.mapper;

import be.kgoris.sociallearningbackend.dto.PropositionDto;
import be.kgoris.sociallearningbackend.entities.Proposition;
import org.springframework.stereotype.Service;

@Service
public class PropositionMapperImpl implements PropositionMapper {
    @Override
    public Proposition fromDtoToModel(PropositionDto propositionDto) {
        return null;
    }

    @Override
    public PropositionDto fromModelToDto(Proposition proposition) {
        return null;
    }
}

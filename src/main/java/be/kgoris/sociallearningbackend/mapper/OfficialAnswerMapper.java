package be.kgoris.sociallearningbackend.mapper;

import be.kgoris.sociallearningbackend.dto.OfficialAnswerDto;
import be.kgoris.sociallearningbackend.entities.OfficialAnswer;

public interface OfficialAnswerMapper {
    OfficialAnswer fromDtoToModel(OfficialAnswerDto officialAnswerDto);
    OfficialAnswerDto fromModelToDto(OfficialAnswer officialAnswer);
}

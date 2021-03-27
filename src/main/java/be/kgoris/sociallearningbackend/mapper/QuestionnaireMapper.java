package be.kgoris.sociallearningbackend.mapper;

import be.kgoris.sociallearningbackend.dto.QuestionnaireDto;
import be.kgoris.sociallearningbackend.entities.Questionnaire;

public interface QuestionnaireMapper {
    QuestionnaireDto fromModelToDto(Questionnaire questionnaire);
    Questionnaire fromDtoToModel(QuestionnaireDto questionnaireDto);
}

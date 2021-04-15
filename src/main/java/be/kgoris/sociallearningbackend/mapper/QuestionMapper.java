package be.kgoris.sociallearningbackend.mapper;

import be.kgoris.sociallearningbackend.dto.QuestionDto;
import be.kgoris.sociallearningbackend.dto.QuestionnaireDto;
import be.kgoris.sociallearningbackend.entities.Question;
import be.kgoris.sociallearningbackend.entities.Questionnaire;

public interface QuestionMapper {
    Question fromDtoToModel(QuestionDto questionDto);
    QuestionDto fromModelToDto(Question question);
    QuestionDto fromModelToDto(Question question, QuestionnaireDto questionnaireDto);
}

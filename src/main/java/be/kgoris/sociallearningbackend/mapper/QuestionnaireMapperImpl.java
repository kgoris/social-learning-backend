package be.kgoris.sociallearningbackend.mapper;

import be.kgoris.sociallearningbackend.dto.QuestionnaireDto;
import be.kgoris.sociallearningbackend.entities.Questionnaire;
import org.springframework.stereotype.Service;

@Service
public class QuestionnaireMapperImpl implements QuestionnaireMapper {
    @Override
    public QuestionnaireDto fromModelToDto(Questionnaire questionnaire) {
        return QuestionnaireDto.builder()
                .id(questionnaire.getId())
                .levelSequence(questionnaire.getLevelSequence())
                .code(questionnaire.getCode())
                .title(questionnaire.getTitle()).build();

    }

    @Override
    public Questionnaire fromDtoToModel(QuestionnaireDto questionnaireDto) {
        return Questionnaire.builder()
                .id(questionnaireDto.getId())
                .levelSequence(questionnaireDto.getLevelSequence())
                .code(questionnaireDto.getCode())
                .title(questionnaireDto.getTitle()).build();
    }
}

package be.kgoris.sociallearningbackend.mapper;

import be.kgoris.sociallearningbackend.dto.QuestionnaireDto;
import be.kgoris.sociallearningbackend.entities.Questionnaire;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionnaireMapperImpl implements QuestionnaireMapper {

    private final QuestionMapper questionMapper;

    @Override
    public QuestionnaireDto fromModelToDto(Questionnaire questionnaire) {
        return QuestionnaireDto.builder()
                .id(questionnaire.getId())
                .levelSequence(questionnaire.getLevelSequence())
                .code(questionnaire.getCode())
                .title(questionnaire.getTitle())
                .questions(questionnaire.getQuestions()
                                            .stream()
                                            .map(questionMapper::fromModelToDto)
                                            .collect(Collectors.toList()))
                .build();

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

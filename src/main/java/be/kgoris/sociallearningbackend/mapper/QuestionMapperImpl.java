package be.kgoris.sociallearningbackend.mapper;

import be.kgoris.sociallearningbackend.dto.QuestionDto;
import be.kgoris.sociallearningbackend.entities.Question;
import org.springframework.stereotype.Service;

@Service
public class QuestionMapperImpl implements QuestionMapper {
    @Override
    public Question fromDtoToModel(QuestionDto questionDto) {
        return null;
    }

    @Override
    public QuestionDto fromModelToDto(Question question) {
        return null;
    }
}

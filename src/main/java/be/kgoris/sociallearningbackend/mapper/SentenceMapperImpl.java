package be.kgoris.sociallearningbackend.mapper;

import be.kgoris.sociallearningbackend.dto.SentenceDto;
import be.kgoris.sociallearningbackend.entities.Sentence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SentenceMapperImpl implements SentenceMapper {

    @Override
    public SentenceDto fromModelToDto(Sentence sentence) {
        return SentenceDto.builder()
                .id(sentence.getId())
                .firstPart(sentence.getFirstPart())
                .expression(sentence.getExpression())
                .secondPart(sentence.getSecondPart())
                .language(sentence.getLanguage())
                .build();
    }
}

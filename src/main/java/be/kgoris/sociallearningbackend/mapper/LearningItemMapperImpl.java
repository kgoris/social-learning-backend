package be.kgoris.sociallearningbackend.mapper;

import be.kgoris.sociallearningbackend.dto.LearningItemDto;
import be.kgoris.sociallearningbackend.entities.LearningItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LearningItemMapperImpl implements LearningItemMapper {

    public final SentenceMapper sentenceMapper;

    @Override
    public LearningItemDto fromModelToDto(LearningItem learningItem) {
        return LearningItemDto.builder()
                .original(learningItem.getOriginal() != null ?
                        sentenceMapper.fromModelToDto(learningItem.getOriginal())
                        : null)
                .translation(learningItem.getTranslation() != null ?
                        sentenceMapper.fromModelToDto(learningItem.getTranslation())
                        : null)
                .id(learningItem.getId())
                .sequenceNumber(learningItem.getSequenceNumber())
                .build();
    }
}

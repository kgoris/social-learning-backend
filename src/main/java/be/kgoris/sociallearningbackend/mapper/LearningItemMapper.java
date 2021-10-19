package be.kgoris.sociallearningbackend.mapper;

import be.kgoris.sociallearningbackend.dto.LearningItemDto;
import be.kgoris.sociallearningbackend.entities.LearningItem;

public interface LearningItemMapper {
    LearningItemDto fromModelToDto(LearningItem learningItem);
}

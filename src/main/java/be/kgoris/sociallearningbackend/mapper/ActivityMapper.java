package be.kgoris.sociallearningbackend.mapper;

import be.kgoris.sociallearningbackend.entities.Activity;
import be.kgoris.sociallearningbackend.dto.ActivityDto;

public interface ActivityMapper {
    Activity fromDtoToModel(ActivityDto activityDto);
    ActivityDto fromModelToDto(Activity activity);
}

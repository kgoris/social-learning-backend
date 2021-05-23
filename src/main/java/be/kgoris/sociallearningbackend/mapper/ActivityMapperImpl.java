package be.kgoris.sociallearningbackend.mapper;

import be.kgoris.sociallearningbackend.entities.Activity;
import be.kgoris.sociallearningbackend.dto.ActivityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityMapperImpl implements ActivityMapper {

    private final PropositionMapper propositionMapper;
    private final StudentMapper studentMapper;

    @Override
    public Activity fromDtoToModel(ActivityDto activityDto) {
        return Activity.builder()
                .id(activityDto.getId())
                .proposition(activityDto.getProposition() != null ? propositionMapper.fromDtoToModel(activityDto.getProposition()) : null)
                .student(activityDto.getStudent() != null ? studentMapper.fromDtoToModel(activityDto.getStudent()) : null)
                .ressourceId(activityDto.getRessourceId())
                .ressourceType(activityDto.getRessourceType())
                .type(activityDto.getType())
                .value(activityDto.getValue())
                .build();
    }

    @Override
    public ActivityDto fromModelToDto(Activity activity) {
        return ActivityDto.builder()
                .id(activity.getId())
                .proposition(activity.getProposition() != null ? propositionMapper.fromModelToDto(activity.getProposition()) : null)
                .student(activity.getStudent() != null ? studentMapper.fromModelToDto(activity.getStudent()) : null)
                .ressourceId(activity.getRessourceId())
                .ressourceType(activity.getRessourceType())
                .type(activity.getType())
                .value(activity.getValue())
                .build();
    }
}

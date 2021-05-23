package be.kgoris.sociallearningbackend.service;

import be.kgoris.sociallearningbackend.dao.ActivityRepository;
import be.kgoris.sociallearningbackend.dto.ActivityDto;
import be.kgoris.sociallearningbackend.dto.StudentDto;
import be.kgoris.sociallearningbackend.entities.Activity;
import be.kgoris.sociallearningbackend.entities.Student;
import be.kgoris.sociallearningbackend.mapper.ActivityMapper;
import be.kgoris.sociallearningbackend.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;
    private final StudentMapper studentMapper;

    @Override
    public void save(ActivityDto activityDto) {
        Activity activity = activityMapper.fromDtoToModel(activityDto);
        activity.setActivityDatetime(LocalDateTime.now());
        activityRepository.save(activity);
    }

    @Override
    public List<ActivityDto> findLastActivityByWorkingStudents() {
        Map<Student, List<Activity>> activiesByStudent = new HashMap<>();
        List<Activity> finalActivites = new ArrayList<>();
        List<Activity> activities = activityRepository.findLastActivitiesByWorkingStudents(LocalDateTime.now().minusMinutes(2));
        activities.forEach(activity -> {
            activiesByStudent.putIfAbsent(activity.getStudent(), new ArrayList<>());
            activiesByStudent.get(activity.getStudent()).add(activity);
        });
        for(Student student : activiesByStudent.keySet()){
            List<Activity> activityForCurrentStudent = activiesByStudent.get(student);
            activityForCurrentStudent.sort(new Comparator<Activity>() {
                @Override
                public int compare(Activity activity, Activity t1) {
                    return activity.getActivityDatetime().compareTo(t1.getActivityDatetime());
                }
            });
            if(CollectionUtils.isNotEmpty(activityForCurrentStudent)){
                finalActivites.add(activityForCurrentStudent.get(activityForCurrentStudent.size() - 1));
            }
        }
        return finalActivites.stream().map(activityMapper::fromModelToDto).collect(Collectors.toList());
    }
}

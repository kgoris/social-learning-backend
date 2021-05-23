package be.kgoris.sociallearningbackend.service;

import be.kgoris.sociallearningbackend.dto.ActivityDto;
import be.kgoris.sociallearningbackend.dto.StudentDto;
import be.kgoris.sociallearningbackend.entities.Activity;

import java.util.List;

public interface ActivityService {
    void save(ActivityDto activityDto);
    List<ActivityDto> findLastActivityByWorkingStudents();
}

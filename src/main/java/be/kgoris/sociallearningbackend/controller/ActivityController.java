package be.kgoris.sociallearningbackend.controller;

import be.kgoris.sociallearningbackend.dto.ActivityDto;
import be.kgoris.sociallearningbackend.dto.StudentDto;
import be.kgoris.sociallearningbackend.service.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/activities")
@AllArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping()
    public void save(@RequestBody ActivityDto activityDto) {
        activityService.save(activityDto);
    }
    //TODO: to fix
    @GetMapping("working-students")
    List<ActivityDto> findLastActivityByWorkingStudents(){
        return activityService.findLastActivityByWorkingStudents();
    }
}

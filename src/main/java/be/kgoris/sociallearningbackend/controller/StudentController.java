package be.kgoris.sociallearningbackend.controller;

import be.kgoris.sociallearningbackend.dto.StudentDto;
import be.kgoris.sociallearningbackend.service.StudentService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/old-students")
@AllArgsConstructor
public class StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;

    @RequestMapping(method = RequestMethod.GET)
    public List<StudentDto> all(){
        return studentService.findAll();
    }

    @GetMapping("{id}")
    public StudentDto findById(@PathVariable(name = "id") String id){
        return studentService.findById(Integer.valueOf(id));
    }

    @RequestMapping("/whoami")
    public StudentDto user() {
        return studentService.whoIamI();
    }

}

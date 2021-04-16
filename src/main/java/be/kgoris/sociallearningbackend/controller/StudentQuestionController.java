package be.kgoris.sociallearningbackend.controller;

import be.kgoris.sociallearningbackend.dto.StudentDto;
import be.kgoris.sociallearningbackend.dto.StudentQuestionDto;
import be.kgoris.sociallearningbackend.service.StudentQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/student-questions")
@RequiredArgsConstructor
public class StudentQuestionController {

    private final StudentQuestionService studentQuestionService;

    @PostMapping("/find-by-student")
    List<StudentQuestionDto> findByStudent(@RequestBody StudentDto studentDto){
        return studentQuestionService.findByStudent(studentDto);
    }

    @GetMapping("{id}")
    StudentQuestionDto findById(@PathVariable(name="id") Integer id){
        return studentQuestionService.findById(id);
    }

    @PostMapping("/create-by-questionnaireId-and-student")
    StudentQuestionDto createStudentQuestion(@RequestParam(name="questionnaireId") Integer questionnaireId,
                                             @RequestBody StudentDto studentDto){
        return studentQuestionService.createByQuestionnaireIdAndStudentDto(questionnaireId, studentDto);
    }

    @PostMapping("/next")
    StudentQuestionDto next(@RequestBody StudentQuestionDto studentQuestionDto){
        return studentQuestionService.next(studentQuestionDto);
    }

    @PostMapping("/previous")
    StudentQuestionDto previous(@RequestBody StudentQuestionDto studentQuestionDto){
        return studentQuestionService.previous(studentQuestionDto);
    }


}

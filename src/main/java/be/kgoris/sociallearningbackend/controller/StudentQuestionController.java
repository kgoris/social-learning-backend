package be.kgoris.sociallearningbackend.controller;

import be.kgoris.sociallearningbackend.dto.ResultsDto;
import be.kgoris.sociallearningbackend.dto.StudentDto;
import be.kgoris.sociallearningbackend.dto.StudentQuestionDto;
import be.kgoris.sociallearningbackend.entities.StudentQuestion;
import be.kgoris.sociallearningbackend.service.StudentQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/student-questions")
@RequiredArgsConstructor
public class StudentQuestionController {

    private final StudentQuestionService studentQuestionService;

    @GetMapping("/find-by-student/{username}")
    public List<StudentQuestionDto> findByStudent(@PathVariable(name = "username") String studentUsername){
        return studentQuestionService.findByStudent(studentUsername);
    }

    @GetMapping("{id}")
    public StudentQuestionDto findById(@PathVariable(name="id") Integer id){
        return studentQuestionService.findById(id);
    }

    @GetMapping("/create-by-questionnaireId-and-student")
    public StudentQuestionDto createStudentQuestion(@RequestParam(name="questionnaireId") Integer questionnaireId,
                                                    @RequestParam(name="username") String username){
        return studentQuestionService.createByQuestionnaireIdAndStudentDto(questionnaireId, username);
    }

    @PostMapping("/next")
    public StudentQuestionDto next(@RequestBody StudentQuestionDto studentQuestionDto){
        return studentQuestionService.next(studentQuestionDto);
    }

    @PostMapping("/previous")
    public StudentQuestionDto previous(@RequestBody StudentQuestionDto studentQuestionDto){
        return studentQuestionService.previous(studentQuestionDto);
    }

    @PostMapping("save")
    public void save(@RequestBody StudentQuestionDto studentQuestionDto){
        studentQuestionService.save(studentQuestionDto);
    }

    @GetMapping("/lock")
    public void lock(@RequestParam(name="questionnaireId") Integer questionnaireId, @RequestParam String studentUsername){
        studentQuestionService.lock(questionnaireId, studentUsername);
    }

    @PostMapping("/results")
    public ResultsDto results(@RequestParam(name="questionnaireId") Integer questionnaireId, @RequestBody StudentDto studentDto){
        return studentQuestionService.getResultsForStudentAndQuestionnaire(studentDto, questionnaireId);
    }

    @GetMapping("/reset")
    public StudentQuestionDto reset(@RequestParam(name="questionnaireId") Integer questionnaireId, @RequestParam(name="username") String username){
        return studentQuestionService.reset(username, questionnaireId);
    }

    @GetMapping("/visit")
    public StudentQuestionDto visit(@RequestParam(name="questionnaireId") Integer questionnaireId, @RequestParam(name="username") String username){
        return studentQuestionService.visit(username, questionnaireId);
    }
}

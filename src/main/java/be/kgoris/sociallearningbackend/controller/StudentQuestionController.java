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

    @PostMapping("/find-by-student")
    public List<StudentQuestionDto> findByStudent(@RequestBody StudentDto studentDto){
        return studentQuestionService.findByStudent(studentDto);
    }

    @GetMapping("{id}")
    public StudentQuestionDto findById(@PathVariable(name="id") Integer id){
        return studentQuestionService.findById(id);
    }

    @PostMapping("/create-by-questionnaireId-and-student")
    public StudentQuestionDto createStudentQuestion(@RequestParam(name="questionnaireId") Integer questionnaireId,
                                             @RequestBody StudentDto studentDto){
        return studentQuestionService.createByQuestionnaireIdAndStudentDto(questionnaireId, studentDto);
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

    @PostMapping("/lock")
    public void lock(@RequestParam(name="questionnaireId") Integer questionnaireId, @RequestBody StudentDto studentDto){
        studentQuestionService.lock(questionnaireId, studentDto);
    }

    @PostMapping("/results")
    public ResultsDto results(@RequestParam(name="questionnaireId") Integer questionnaireId, @RequestBody StudentDto studentDto){
        return studentQuestionService.getResultsForStudentAndQuestionnaire(studentDto, questionnaireId);
    }

    @PostMapping("/reset")
    public StudentQuestionDto reset(@RequestParam(name="questionnaireId") Integer questionnaireId, @RequestBody StudentDto studentDto){
        return studentQuestionService.reset(studentDto, questionnaireId);
    }

    @PostMapping("/visit")
    public StudentQuestionDto visit(@RequestParam(name="questionnaireId") Integer questionnaireId, @RequestBody StudentDto studentDto){
        return studentQuestionService.visit(studentDto, questionnaireId);
    }
}

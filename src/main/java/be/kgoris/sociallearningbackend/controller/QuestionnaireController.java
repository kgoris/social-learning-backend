package be.kgoris.sociallearningbackend.controller;

import be.kgoris.sociallearningbackend.allenum.AccessType;
import be.kgoris.sociallearningbackend.dto.QuestionnaireDto;
import be.kgoris.sociallearningbackend.dto.QuestionnaireQueryDto;
import be.kgoris.sociallearningbackend.service.QuestionnaireService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/questionnaires")
@AllArgsConstructor
public class QuestionnaireController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);
    private final QuestionnaireService questionnaireService;


    @PostMapping("/find_by_student_and_access_type")
    public List<QuestionnaireDto> findQuestionnairesByStudent(@RequestBody QuestionnaireQueryDto query){
        LOGGER.info("findQuestionnairesByStudentAndAccessType");
        return questionnaireService.getQuestionnairesByStudentAndAccessType(query.getStudentDto(),
                query.getAccessType());
    }
}

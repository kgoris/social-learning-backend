package be.kgoris.sociallearningbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentQuestionDto {
    private Integer id;
    private AnswerDto answer;
    private StudentDto student;
    private QuestionDto question;
    private QuestionnaireDto questionnaire;
    private boolean locked;
}

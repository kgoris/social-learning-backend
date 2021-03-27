package be.kgoris.sociallearningbackend.dto;

import be.kgoris.sociallearningbackend.entities.Student;
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
    private AnswerDto answerDto;
    private StudentDto studentDto;
}

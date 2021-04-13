package be.kgoris.sociallearningbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto {
    private Integer id;
    private String firstName;
    private String name;
    private String username;
    private String password;
    private StudentGroupDto studentGroup;
    private StudentQuestionDto studentQuestionDto;
    private StudentDto studentObserved;
}

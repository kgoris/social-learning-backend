package be.kgoris.sociallearningbackend.websocket;

import be.kgoris.sociallearningbackend.dto.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityDto {
    private Integer questionnaire;
    private String buttonClicked;
    private StudentDto studentDto;
}

package be.kgoris.sociallearningbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionnaireDto  {
    private Integer id;
    private Integer levelSequence;
    private String title;
    private String code;
}

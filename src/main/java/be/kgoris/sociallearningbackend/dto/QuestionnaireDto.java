package be.kgoris.sociallearningbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionnaireDto  {
    private Integer id;
    private Integer levelSequence;
    private String title;
    private String code;
    private Integer questionCount;
    private List<QuestionDto> questions;
}

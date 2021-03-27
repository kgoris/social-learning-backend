package be.kgoris.sociallearningbackend.dto;

import be.kgoris.sociallearningbackend.allenum.AccessType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccessDto {
    private Integer id;
    private QuestionnaireDto questionnaire;
    private AccessType accessType;
    private Integer studentGroupId;
}

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
public class QuestionnaireQueryDto {
    private StudentDto studentDto;
    private AccessType accessType;
}

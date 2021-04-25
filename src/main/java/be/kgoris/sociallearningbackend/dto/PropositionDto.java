package be.kgoris.sociallearningbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropositionDto {
    private Integer id;
    private String value;
    private Integer officialAnswerId;
    private String questionId;
}

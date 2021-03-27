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
public class OfficialAnswerDto {
    private Integer id;
    private String value;
    private List<PropositionDto> propositionDtos;
}

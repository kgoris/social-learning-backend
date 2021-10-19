package be.kgoris.sociallearningbackend.dto;

import be.kgoris.sociallearningbackend.allenum.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SentenceDto {
    private Integer id;
    private String firstPart;
    private String secondPart;
    private String expression;
    private Language language;
}

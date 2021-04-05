package be.kgoris.sociallearningbackend.dto;

import be.kgoris.sociallearningbackend.allenum.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDto {
    private Integer id;
    private String title;
    private QuestionType type;
    private Integer sequenceNumber;
    private OfficialAnswerDto officialAnswer;
    private List<PropositionDto> propositions;
    private Integer questionnaireId;
}

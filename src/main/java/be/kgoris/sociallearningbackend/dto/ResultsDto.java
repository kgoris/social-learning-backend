package be.kgoris.sociallearningbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultsDto {
    private QuestionnaireDto questionnaire;
    private List<ErrorDto> errors;

    public ResultsDto(QuestionnaireDto questionnaire){
        this.questionnaire = questionnaire;
    }

    public void addError(ErrorDto error){
        if(this.errors == null){
            this.errors = new ArrayList<>();
        }
        this.errors.add(error);
    }
}

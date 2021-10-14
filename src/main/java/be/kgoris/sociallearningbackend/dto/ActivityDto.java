package be.kgoris.sociallearningbackend.dto;

import be.kgoris.sociallearningbackend.allenum.ActionType;
import be.kgoris.sociallearningbackend.allenum.RessourceType;
import be.kgoris.sociallearningbackend.dto.PropositionDto;
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
    private Integer id;
    private RessourceType ressourceType;
    private ActionType type;
    private Integer ressourceId;
    private String studentUsername;
    private PropositionDto proposition;
    private String value;
}

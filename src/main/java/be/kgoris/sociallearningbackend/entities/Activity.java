package be.kgoris.sociallearningbackend.entities;

import be.kgoris.sociallearningbackend.allenum.ActionType;
import be.kgoris.sociallearningbackend.allenum.RessourceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name="ACTIVITY")
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private RessourceType ressourceType;
    @Enumerated(EnumType.STRING)
    private ActionType type;
    private Integer ressourceId;

    @ManyToOne
    private Student student;
    @ManyToOne
    private Proposition proposition;
    private String value;
    private LocalDateTime activityDatetime;
}

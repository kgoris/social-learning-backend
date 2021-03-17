package be.kgoris.sociallearningbackend.entities;

import be.kgoris.sociallearningbackend.allenum.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name="QUESTION")
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    @Enumerated(EnumType.STRING)
    private QuestionType type;
    private Integer sequenceNumber;
    @ManyToOne
    private Questionnaire questionnaire;
    @ManyToOne
    private OfficialAnswer officialAnswer;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Proposition> propositions;

}

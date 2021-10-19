package be.kgoris.sociallearningbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name="LEARNING_ITEM")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LearningItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer sequenceNumber;

    @ManyToOne
    private Sentence original;
    @ManyToOne
    private Sentence translation;

    @ManyToOne
    private Questionnaire questionnaire;
}

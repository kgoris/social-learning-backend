package be.kgoris.sociallearningbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name="QUESTIONNAIRE")
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Questionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer levelSequence;
    private String title;

    @OneToMany(mappedBy = "questionnaire", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;
}

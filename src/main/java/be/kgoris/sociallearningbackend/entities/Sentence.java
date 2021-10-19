package be.kgoris.sociallearningbackend.entities;

import be.kgoris.sociallearningbackend.allenum.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name="SENTENCE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sentence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstPart;
    private String secondPart;
    private String expression;

    @Enumerated(EnumType.STRING)
    private Language language;

}

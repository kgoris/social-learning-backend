package be.kgoris.sociallearningbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name="ANSWER")
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Proposition proposition;

    private String value;

}

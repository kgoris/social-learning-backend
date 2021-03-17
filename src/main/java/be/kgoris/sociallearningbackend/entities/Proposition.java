package be.kgoris.sociallearningbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name="PROPOSITION")
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Proposition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String value;

    @ManyToOne
    private Question question;
    @ManyToOne
    private OfficialAnswer officialAnswer;
}

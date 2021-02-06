package be.kgoris.sociallearningbackend.entities;

import be.kgoris.sociallearningbackend.allenum.AccessType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name="ACCESS")
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Access {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Questionnaire questionnaire;
    private AccessType accessType;
    @ManyToOne
    private StudentGroup studentGroup;
}

package be.kgoris.sociallearningbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name="STUDENT_QUESTION")
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Answer answer;

    @ManyToOne
    private Student student;
}

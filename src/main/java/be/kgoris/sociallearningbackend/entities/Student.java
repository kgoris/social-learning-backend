package be.kgoris.sociallearningbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name="STUDENT")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String name;
    private String email;
    private String password;

    @ManyToOne
    private StudentGroup group;

    @ManyToOne
    private StudentQuestion currentQuestion;

    @ManyToOne
    private Student studentObserved;
}

package be.kgoris.sociallearningbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name="STUDENT_GROUP")
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String code;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Student> students;

    @OneToMany(mappedBy = "studentGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Access> accesses;
}

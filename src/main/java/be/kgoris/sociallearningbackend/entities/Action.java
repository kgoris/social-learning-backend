package be.kgoris.sociallearningbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name="Action")
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate time;
    private AccessType accessType;
    @ManyToOne
    private Student executer;
    @ManyToOne
    private Student target;
}

package be.kgoris.sociallearningbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name="OFFICIAL_ANSWER")
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfficialAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "officialAnswer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Proposition> propositions;
    private String value;

}

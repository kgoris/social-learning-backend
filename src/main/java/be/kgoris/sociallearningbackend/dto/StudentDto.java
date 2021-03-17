package be.kgoris.sociallearningbackend.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto {
    private Integer id;
    private String firstName;
    private String name;
    private String email;
    private String password;
}

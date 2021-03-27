package be.kgoris.sociallearningbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentGroupDto {
    private Integer id;
    private String name;
    private String code;
    private List<AccessDto> access;
}

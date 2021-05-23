package be.kgoris.sociallearningbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityStudentDto {
    private ActivityDto activityDto;
    private StudentDto studentDto;
}

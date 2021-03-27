package be.kgoris.sociallearningbackend.mapper;

import be.kgoris.sociallearningbackend.dto.StudentDto;
import be.kgoris.sociallearningbackend.entities.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentMapperImpl implements StudentMapper {
    @Override
    public StudentDto fromModelToDto(Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .name(student.getName())
                .email(student.getEmail())
                .password(student.getPassword())
                .build();
    }

    @Override
    public Student fromDtoToModel(StudentDto studentDto) {
        return Student.builder()
                .email(studentDto.getEmail())
                .firstName(studentDto.getFirstName())
                .name(studentDto.getName())
                .password(studentDto.getPassword())
                .build();
    }
}

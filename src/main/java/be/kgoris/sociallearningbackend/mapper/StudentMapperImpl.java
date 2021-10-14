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
                .username(student.getUsername())
                .password(student.getPassword())
                .studentObserved(student.getStudentObserved() != null ? fromModelToDto(student.getStudentObserved()) : null)
                .build();
    }

    @Override
    public Student fromDtoToModel(StudentDto studentDto) {
        return Student.builder()
                .id(studentDto.getId())
                .username(studentDto.getUsername())
                .firstName(studentDto.getFirstName())
                .name(studentDto.getName())
                .password(studentDto.getPassword())
                .studentObserved(studentDto.getStudentObserved() != null ? fromDtoToModel(studentDto.getStudentObserved()) : null)
                .build();
    }
}

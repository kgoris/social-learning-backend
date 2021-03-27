package be.kgoris.sociallearningbackend.mapper;

import be.kgoris.sociallearningbackend.dto.StudentDto;
import be.kgoris.sociallearningbackend.entities.Student;

public interface StudentMapper {
    StudentDto fromModelToDto(Student student);
    Student fromDtoToModel(StudentDto studentDto);
}

package be.kgoris.sociallearningbackend.service;

import be.kgoris.sociallearningbackend.dto.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> getAllStudents();
}

package be.kgoris.sociallearningbackend.service;

import be.kgoris.sociallearningbackend.dto.StudentDto;
import be.kgoris.sociallearningbackend.entities.Student;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface StudentService {
    List<StudentDto> findAll();
    StudentDto findById(Integer id);
    void resetCredentials();
    StudentDto findDtoByUsername(String login) throws UsernameNotFoundException;
    Student findByUsername(String username);
    void update(StudentDto studentDto);
    StudentDto whoIamI();
}

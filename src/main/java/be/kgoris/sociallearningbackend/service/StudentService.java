package be.kgoris.sociallearningbackend.service;

import be.kgoris.sociallearningbackend.dto.StudentDto;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface StudentService {
    List<StudentDto> findAll();
    StudentDto findById(Integer id);
    void create(StudentDto studentDto);
    void resetCredentials();
    StudentDto findByLogin( String login) throws UsernameNotFoundException;
    void update(StudentDto studentDto);
    StudentDto whoIamI();
}

package be.kgoris.sociallearningbackend.service;

import be.kgoris.sociallearningbackend.dao.StudentDao;
import be.kgoris.sociallearningbackend.dto.StudentDto;
import be.kgoris.sociallearningbackend.entities.Student;
import be.kgoris.sociallearningbackend.mapper.StudentMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;
    private final StudentMapper studentMapper;

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentDao.findAll();
        return students.stream()
                .map(studentMapper::fromStudentToUserDto)
                .collect(Collectors.toList());
    }
}

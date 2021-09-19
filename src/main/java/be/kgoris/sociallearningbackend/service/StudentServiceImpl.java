package be.kgoris.sociallearningbackend.service;

import be.kgoris.sociallearningbackend.dao.AutorityRepository;
import be.kgoris.sociallearningbackend.dao.StudentRepository;
import be.kgoris.sociallearningbackend.dto.StudentDto;
import be.kgoris.sociallearningbackend.entities.Authority;
import be.kgoris.sociallearningbackend.entities.Student;
import be.kgoris.sociallearningbackend.mapper.StudentMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final PasswordEncoder passwordEncoder;
    private final AutorityRepository autorityRepository;
    @Override
    public List<StudentDto> findAll() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(studentMapper::fromModelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto findById(Integer id) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isEmpty()) {
            return null;
        }
        return studentMapper.fromModelToDto(student.get());
    }

    @Override
    public void create(StudentDto studentDto) {
        Student student = studentMapper.fromDtoToModel(studentDto);
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        Authority authority = autorityRepository.findByName("ROLE_USER");
        student.addAuthority(authority);
        studentRepository.save(student);
    }

    @Override
    public void resetCredentials() {
    }

    @Override
    public StudentDto findByLogin(String login) throws UsernameNotFoundException {
        Optional<Student> student = studentRepository.findByUsername(login);
        if(student.isEmpty()){
            return null;
        }
        return studentMapper.fromModelToDto(student.get());
    }

    @Override
    public void update(StudentDto studentDto) {
        Optional<Student> studentOpt = studentRepository.findById(studentDto.getId());
        if(studentOpt.isPresent()){
            Student student = studentOpt.get();
            String encodedPassword = passwordEncoder.encode(studentDto.getPassword());
            if(!student.getPassword().equals(passwordEncoder.encode(studentDto.getPassword()))){
                student.setPassword(encodedPassword);
            }
            student.setFirstName(studentDto.getFirstName());
            student.setName(studentDto.getName());
            student.setUsername(studentDto.getUsername());
            student.setStudentObserved(studentMapper.fromDtoToModel(studentDto.getStudentObserved()));

            studentRepository.save(student);
           /* List<BigInteger> autorityIds = autorityRepository.findAutorityIdsByUserId(user.getId());
            for(BigInteger autorityId : autorityIds){
                Authority authority = autorityRepository.findById(Long.valueOf(autorityId.toString()));
                user.addAuthority(authority);
            }*/
        }
    }

    @Override
    public StudentDto whoIamI() {
        Student student =  (Student) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        if(student != null){
            return studentMapper.fromModelToDto(student);
        }
        return null;
    }


}

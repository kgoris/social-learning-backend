package be.kgoris.sociallearningbackend.dao;

import be.kgoris.sociallearningbackend.entities.Student;
import be.kgoris.sociallearningbackend.entities.StudentQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentQuestionRepository extends JpaRepository<StudentQuestion, Integer> {
    List<StudentQuestion> findAllByStudent(Student student);
}

package be.kgoris.sociallearningbackend.dao;

import be.kgoris.sociallearningbackend.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDao extends JpaRepository<Student, Integer> {
}

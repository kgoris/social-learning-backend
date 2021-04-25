package be.kgoris.sociallearningbackend.dao;

import be.kgoris.sociallearningbackend.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}

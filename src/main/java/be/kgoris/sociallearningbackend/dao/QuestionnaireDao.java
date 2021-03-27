package be.kgoris.sociallearningbackend.dao;

import be.kgoris.sociallearningbackend.allenum.AccessType;
import be.kgoris.sociallearningbackend.entities.Questionnaire;
import be.kgoris.sociallearningbackend.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionnaireDao extends JpaRepository<Questionnaire, Integer> {

    @Query("SELECT q FROM STUDENT s join s.group g join g.accesses a join a.questionnaire q where a.accessType = ?1")
    List<Questionnaire> findAllByStudentAndAccessType(Student student, AccessType accessType);
}

package be.kgoris.sociallearningbackend.dao;

import be.kgoris.sociallearningbackend.entities.Activity;
import be.kgoris.sociallearningbackend.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {
    @Query("SELECT distinct a FROM ACTIVITY a join a.student s where a.activityDatetime > :datelimit")
    List<Activity> findLastActivitiesByWorkingStudents(@Param("datelimit") LocalDateTime datelimit);
}

package be.kgoris.sociallearningbackend.dao;

import be.kgoris.sociallearningbackend.entities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {
    @Query("SELECT distinct a FROM ACTIVITY a where a.activityDatetime > :datelimit and not (a.ressourceType = 'QUESTIONNAIRE' and a.type = 'TYPE')")
    List<Activity> findLastActivitiesByWorkingStudents(@Param("datelimit") LocalDateTime datelimit);
}

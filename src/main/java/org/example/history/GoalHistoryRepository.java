package org.example.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GoalHistoryRepository extends JpaRepository<Goal, Long> {
    @Query("SELECT g FROM Goal g JOIN FETCH g.subtasks JOIN g.section s WHERE s.name = :sectionName")
    List<Goal> findAllBySectionName(@Param("sectionName") String sectionName);
}


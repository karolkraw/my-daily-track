package org.example.history;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalHistoryRepository extends JpaRepository<Goal, Long> {}

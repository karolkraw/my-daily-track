package org.example.Streak;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StreakRepository extends JpaRepository <Streak, Long> {
    Streak findByName(String name);
}

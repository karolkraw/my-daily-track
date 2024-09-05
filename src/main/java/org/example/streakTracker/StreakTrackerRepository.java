package org.example.streakTracker;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StreakTrackerRepository extends JpaRepository <StreakTracker, Long> {
    StreakTracker findByName(String name);
}

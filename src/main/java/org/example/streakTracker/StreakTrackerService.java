package org.example.streakTracker;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class StreakTrackerService {
    StreakTrackerRepository streakTrackerRepository;
    public StreakTrackerService(StreakTrackerRepository streakTrackerRepository) {
        this.streakTrackerRepository = streakTrackerRepository;
    }

    StreakTracker getStreakTrackerByName(String name) {
        return streakTrackerRepository.findByName(name);
    }

    StreakTracker createStreakTracker(StreakTracker streakTracker) {
        streakTracker.streakLength = calculateStreakLength(streakTracker.getStreakCreated());
        return streakTrackerRepository.save(streakTracker);
    }

    private Long calculateStreakLength(LocalDate streakCreated) {
        return ChronoUnit.DAYS.between(streakCreated, LocalDate.now());
    }

    public void resetStreak(StreakTracker streakTracker) {
        streakTracker.setStreakCreated(LocalDate.now());
        streakTracker.setStreakLength(0L);
        streakTrackerRepository.save(streakTracker);
    }
}

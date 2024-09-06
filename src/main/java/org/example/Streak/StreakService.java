package org.example.Streak;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class StreakService {
    StreakRepository streakTrackerRepository;
    public StreakService(StreakRepository streakTrackerRepository) {
        this.streakTrackerRepository = streakTrackerRepository;
    }

    Streak getStreakTrackerByName(String name) {
        return streakTrackerRepository.findByName(name);
    }

    Streak createStreakTracker(Streak streakTracker) {
        streakTracker.streakLength = calculateStreakLength(streakTracker.getStreakCreated());
        return streakTrackerRepository.save(streakTracker);
    }

    private Long calculateStreakLength(LocalDate streakCreated) {
        return ChronoUnit.DAYS.between(streakCreated, LocalDate.now());
    }

    public void resetStreak(Streak streakTracker) {
        streakTracker.setStreakCreated(LocalDate.now());
        streakTracker.setStreakLength(0L);
        streakTrackerRepository.save(streakTracker);
    }
}

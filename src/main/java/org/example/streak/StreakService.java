package org.example.streak;

import org.example.section.Section;
import org.example.section.SectionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class StreakService {
    private StreakRepository streakRepository;
    private SectionService sectionService;
    private final int STREAK_FIRST_DAY = 1;
    public StreakService(StreakRepository streakRepository, SectionService sectionService) {
        this.streakRepository = streakRepository;
        this.sectionService = sectionService;
    }

    Streak getStreakByName(String name) {
        return streakRepository.findByName(name);
    }

    List<Streak> getStreaksBySectionName(String sectionName) {
        return streakRepository.findBySection_Name(sectionName);
    }

    Streak createStreak(Streak streak, String sectionName) {
        streak.days = STREAK_FIRST_DAY;
        Section section = sectionService.getSectionByName(sectionName);
        streak.setSection(section);
        return streakRepository.save(streak);
    }

    void resetStreak(Streak streak) {
        streak.setStartDate(LocalDate.now());
        streak.setDays(0);
        streakRepository.save(streak);
    }

    @Transactional
    void deleteStreak(String name) {
        streakRepository.deleteByName(name);
    }
}

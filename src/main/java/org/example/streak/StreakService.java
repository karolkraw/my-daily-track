package org.example.streak;

import org.example.kafka.KafkaMessageConsumer;
import org.example.section.Section;
import org.example.section.SectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class StreakService {
    private static final Logger logger = LoggerFactory.getLogger(KafkaMessageConsumer.class);
    private StreakRepository streakRepository;
    private SectionService sectionService;
    private final int STREAK_FIRST_DAY = 1;
    public StreakService(StreakRepository streakRepository, SectionService sectionService) {
        this.streakRepository = streakRepository;
        this.sectionService = sectionService;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    //@Scheduled(cron = "0 */2 * * * ?")
    public void incrementStreakDays() {
        List<Streak> streaks = streakRepository.findAll();
        for (Streak streak: streaks) streak.setDays(streak.getDays() + 1);
        streakRepository.saveAll(streaks);
        logger.info("streaks incremented");
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

    Streak resetStreak(Streak currentStreak, String sectionName) {
        Streak streak = streakRepository.findByNameAndSection_Name(currentStreak.name, sectionName);
        streak.setStartDate(LocalDate.now().plusDays(1));
        streak.setDays(0);
        return streakRepository.save(streak);
    }

    @Transactional
    void deleteStreak(String name, String sectionName) {
        streakRepository.deleteByNameAndSection_Name(name, sectionName);
    }
}

package org.example.streak;

import org.example.kafka.KafkaMessageConsumer;
import org.example.section.Section;
import org.example.section.SectionService;
import org.example.user.User;
import org.example.user.UserService;
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
    private final StreakRepository streakRepository;
    private final SectionService sectionService;
    private final UserService userService;

    private final int STREAK_FIRST_DAY = 1;
    public StreakService(StreakRepository streakRepository, SectionService sectionService, UserService userService) {
        this.streakRepository = streakRepository;
        this.sectionService = sectionService;
        this.userService = userService;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void incrementStreakDays() {
        List<Streak> streaks = streakRepository.findAll();
        for (Streak streak : streaks) {
            streak.setDays(streak.getDays() + 1);
        }
        streakRepository.saveAll(streaks);
        logger.info("Streaks incremented");
    }

    public List<Streak> getStreaksBySectionName(String sectionName) {
        User currentUser = userService.getCurrentUser();
        return streakRepository.findBySection_NameAndSection_User(sectionName, currentUser);
    }

    public Streak createStreak(Streak streak, String sectionName) {
        Section section = sectionService.getSectionByName(sectionName);
        streak.setDays(1);
        streak.setSection(section);
        return streakRepository.save(streak);
    }

    public Streak resetStreak(Streak currentStreak, String sectionName) {
        User currentUser = userService.getCurrentUser();
        Streak streak = streakRepository.findByNameAndSection_NameAndSection_User(currentStreak.getName(), sectionName, currentUser)
                .orElseThrow(() -> new RuntimeException("Streak not found"));
        streak.setStartDate(LocalDate.now().plusDays(1));
        streak.setDays(0);
        return streakRepository.save(streak);
    }

    @Transactional
    public void deleteStreak(String name, String sectionName) {
        User currentUser = userService.getCurrentUser();
        streakRepository.deleteByNameAndSection_NameAndSection_User(name, sectionName, currentUser);
    }
}

package org.example.streak.dto;

import org.example.streak.Streak;

import java.util.List;
import java.util.stream.Collectors;

public class StreakDtoMapper {
    public static StreakDto mapStreakToDto(Streak streak) {
        if (streak == null) {
            return null;
        }
        return new StreakDto(streak.getName(), streak.getStartDate(), streak.getDays());
    }

    public static List<StreakDto> mapStreaksToDto(List<Streak> streaks) {
        if (streaks == null) {
            return null;
        }
        return streaks.stream()
                .map(StreakDtoMapper::mapStreakToDto)
                .collect(Collectors.toList());
    }

    public static Streak mapDtoToStreak(StreakDto streakDto) {
        if (streakDto == null) {
            return null;
        }
        Streak streak = new Streak();
        streak.setName(streakDto.getName());
        streak.setDays(streakDto.getDays());
        streak.setStartDate(streakDto.getStartDate());
        return streak;
    }
}


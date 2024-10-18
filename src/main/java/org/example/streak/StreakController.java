package org.example.streak;

import org.example.streak.dto.StreakDto;
import org.example.streak.dto.StreakDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.streak.dto.StreakDtoMapper.mapDtoToStreak;
import static org.example.streak.dto.StreakDtoMapper.mapStreakToDto;

@RestController
@RequestMapping("/api/streaks")
public class StreakController {
    StreakService streakService;
    public StreakController(StreakService streakService) {
        this.streakService = streakService;
    }

    @GetMapping("{sectionName}")
    ResponseEntity<List<StreakDto>> getStreaksBySectionName(@PathVariable String sectionName) {
        List<Streak> streaks = streakService.getStreaksBySectionName(sectionName);
        List<StreakDto> streaksDto = streaks.stream().map(StreakDtoMapper::mapStreakToDto).toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(streaksDto);
    }

    @PostMapping("{sectionName}")
    ResponseEntity<StreakDto> createStreak(@RequestBody StreakDto streakDto, @PathVariable String sectionName) {
        Streak created = streakService.createStreak(mapDtoToStreak(streakDto), sectionName);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapStreakToDto(created));
    }

    @DeleteMapping("{sectionName}/{name}")
    ResponseEntity<Object> deleteStreak(@PathVariable String sectionName, @PathVariable String name) {
        streakService.deleteStreak(name, sectionName);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{sectionName}")
    ResponseEntity<Object> resetStreak(@RequestBody StreakDto streakDto, @PathVariable String sectionName) {
        Streak updatedStreak = streakService.resetStreak(mapDtoToStreak(streakDto), sectionName);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapStreakToDto(updatedStreak));
    }
}

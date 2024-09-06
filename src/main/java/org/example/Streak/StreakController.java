package org.example.Streak;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/streak")
public class StreakController {
    StreakService streakTrackerService;
    public StreakController(StreakService streakTrackerService) {
        this.streakTrackerService = streakTrackerService;
    }

    @GetMapping("{name}")
    ResponseEntity<Streak> getCurrentStreakByName(@PathVariable String name) {
        Streak streakTracker = streakTrackerService.getStreakTrackerByName(name);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(streakTracker);
    }

    @PostMapping
    ResponseEntity<Streak> createStreakTracker(@RequestBody Streak streakTracker) {
        Streak created = streakTrackerService.createStreakTracker(streakTracker);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }
}

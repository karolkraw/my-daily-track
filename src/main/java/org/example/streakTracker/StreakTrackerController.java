package org.example.streakTracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/streak")
public class StreakTrackerController {
    StreakTrackerService streakTrackerService;
    public StreakTrackerController(StreakTrackerService streakTrackerService) {
        this.streakTrackerService = streakTrackerService;
    }

    @GetMapping("{name}")
    ResponseEntity<StreakTracker> getCurrentStreakByName(@PathVariable String name) {
        StreakTracker streakTracker = streakTrackerService.getStreakTrackerByName(name);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(streakTracker);
    }

    @PostMapping
    ResponseEntity<StreakTracker> createStreakTracker(@RequestBody StreakTracker streakTracker) {
        StreakTracker created = streakTrackerService.createStreakTracker(streakTracker);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }
}

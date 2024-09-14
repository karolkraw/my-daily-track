package org.example.history;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/history")
public class GoalHistoryController {
    private final GoalHistoryService goalHistoryService;

    public GoalHistoryController(GoalHistoryService goalHistoryService) {
        this.goalHistoryService = goalHistoryService;
    }

}

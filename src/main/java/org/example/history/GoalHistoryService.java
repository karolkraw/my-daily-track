package org.example.history;

import org.springframework.stereotype.Service;

@Service
public class GoalHistoryService {
    private final GoalHistoryRepository repository;

    public GoalHistoryService(GoalHistoryRepository repository) {
        this.repository = repository;
    }
}

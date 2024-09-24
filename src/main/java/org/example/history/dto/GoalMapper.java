package org.example.history.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.history.Goal;
import org.example.history.Subtask;

import java.util.List;
import java.util.stream.Collectors;

public class GoalMapper {

    public static GoalDto toDto(Goal goal) {
        if (goal == null) {
            return null;
        }

        List<SubtaskDto> subtaskDtos = goal.subtasks.stream()
                .map(GoalMapper::toDto)
                .collect(Collectors.toList());

        return new GoalDto(
                goal.title,
                goal.description,
                goal.completedDate,
                goal.createdDate,
                subtaskDtos
        );
    }

    public static SubtaskDto toDto(Subtask subtask) {
        if (subtask == null) {
            return null;
        }

        return new SubtaskDto(
                subtask.title,
                subtask.description,
                subtask.completedDate,
                subtask.createdDate
        );
    }

    public static List<GoalDto> toDtoList(List<Goal> goals) {
        return goals.stream()
                .map(GoalMapper::toDto)
                .collect(Collectors.toList());
    }
}

package org.example.history.dto;

import org.example.history.Goal;
import org.example.history.Subtask;

import java.util.List;
import java.util.stream.Collectors;

import static org.example.utils.DateUtils.convertLocalDateToString;
import static org.example.utils.DateUtils.convertStringToLocalDate;

public class GoalMapper {
    public static Goal mapDtoToGoal(GoalDto goalDto) {
        if (goalDto == null) {
            return null;
        }
        Goal goal = new Goal();
        goal.title = goalDto.getTitle();
        goal.description = goalDto.getDescription();
        goal.createdDate = convertStringToLocalDate(goalDto.getCreatedDate());
        goal.completedDate = convertStringToLocalDate(goalDto.getCompletedDate());
        goal.subtasks = goalDto.getSubtasks().stream().map(GoalMapper::dtoToSubtask).toList();
        return goal;
    }

    public static GoalDto goalToDto(Goal goal) {
        if (goal == null) {
            return null;
        }

        List<SubtaskDto> subtaskDtos = goal.subtasks.stream()
                .map(GoalMapper::subtaskToDto)
                .collect(Collectors.toList());

        GoalDto goalDto = new GoalDto();
        goalDto.title = goal.title;
        goalDto.description = goal.description;
        goalDto.createdDate = convertLocalDateToString(goal.createdDate);
        goalDto.completedDate = convertLocalDateToString(goal.completedDate);
        goalDto.sectionName = goal.getSection().getName();
        goalDto.subtasks = subtaskDtos;

        return goalDto;
    }

    public static SubtaskDto subtaskToDto(Subtask subtask) {
        if (subtask == null) {
            return null;
        }

        SubtaskDto subtaskDto = new SubtaskDto();
        subtaskDto.title = subtask.title;
        subtaskDto.description = subtask.description;
        subtaskDto.createdDate = convertLocalDateToString(subtask.createdDate);
        subtaskDto.completedDate = convertLocalDateToString(subtask.completedDate);

        return subtaskDto;
    }

    public static Subtask dtoToSubtask(SubtaskDto subtaskDto) {
        if (subtaskDto == null) {
            return null;
        }

        Subtask subtask = new Subtask();
        subtask.title = subtaskDto.title;
        subtask.description = subtaskDto.description;
        subtask.createdDate = convertStringToLocalDate(subtaskDto.createdDate);
        subtask.completedDate = convertStringToLocalDate(subtaskDto.completedDate);

        return subtask;
    }

    public static List<GoalDto> goalsToDtoList(List<Goal> goals) {
        return goals.stream()
                .map(GoalMapper::goalToDto)
                .collect(Collectors.toList());
    }
}

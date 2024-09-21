package org.example.kafka;

import org.example.history.Goal;
import org.example.history.Subtask;
import org.example.reflection.dto.ReflectionDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GoalMapper {
    public static GoalMessage mapGoalToMessage(Goal goal) {
        if (goal == null) {
            return null;
        }
        return GoalMessage.builder().title(goal.title).description(goal.description)
                .createdDate(goal.createdDate).completedDate(goal.completedDate).build();
    }

    private String title;

    //@JsonProperty("description")
    private String description;
    private String createdDate;
    private String completedDate;
    private List<Subtask> subtasks;


    public static LocalDate convertStringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate.parse(date, formatter);
        return LocalDate.parse(date, formatter);
    }

    public static String convertLocalDateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return date.format(formatter);
    }
}

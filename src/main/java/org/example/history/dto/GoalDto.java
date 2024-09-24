package org.example.history.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GoalDto {
    private String title;
    private String description;
    private String completedDate;
    private String createdDate;
    private List<SubtaskDto> subtasks;
}

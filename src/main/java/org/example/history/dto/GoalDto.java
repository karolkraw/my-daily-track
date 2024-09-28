package org.example.history.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoalDto {
    String title;
    String description;
    String completedDate;
    String createdDate;
    String sectionName;
    List<SubtaskDto> subtasks;
}

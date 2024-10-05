package org.example.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.history.Subtask;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoalMessage {
    //@JsonProperty("title")
    private String title;
    //@JsonProperty("description")
    private String description;
    private String createdDate;
    private String completedDate;
    private List<Subtask> subtasks;
}

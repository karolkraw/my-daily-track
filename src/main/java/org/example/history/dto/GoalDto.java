package org.example.history.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    String username;

    @JsonCreator
    public static GoalDto fromJson(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, GoalDto.class);
    }
}

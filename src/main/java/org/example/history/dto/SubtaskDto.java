package org.example.history.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubtaskDto {
    private String title;
    private String description;
    private String completedDate;
    private String createdDate;
}

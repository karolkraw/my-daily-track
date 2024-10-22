package org.example.history.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubtaskDto {
    String title;
    String description;
    String createdDate;
    String completedDate;
}

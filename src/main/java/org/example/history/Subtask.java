package org.example.history;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "subtasks")
public class Subtask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String completedDate;
    private String createdDate;

    @ManyToOne
    @JoinColumn(name = "goal_id")
    private Goal goal;
}

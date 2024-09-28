package org.example.history;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "subtasks")
public class Subtask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String title;
    public String description;
    public LocalDate completedDate;
    public LocalDate createdDate;

    @ManyToOne
    @JoinColumn(name = "goal_id")
    @JsonIgnore
    public Goal goal;
}

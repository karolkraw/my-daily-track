package org.example.history;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "subtasks")
public class Subtask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String title;
    public String description;
    public String completedDate;
    public String createdDate;

    @ManyToOne
    @JoinColumn(name = "goal_id")
    public Goal goal;
}

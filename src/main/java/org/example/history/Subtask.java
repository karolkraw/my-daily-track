package org.example.history;

import jakarta.persistence.*;

@Entity
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

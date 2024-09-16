package org.example.history;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String completedDate;
    private String createdDate;
    private boolean isHistory = false;

    @OneToMany(mappedBy = "goal")
    private List<Subtask> subtasks;
}

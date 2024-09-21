package org.example.history;

import jakarta.persistence.*;
import org.example.section.Section;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "goals")
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String title;
    public String description;
    public String completedDate;
    public String createdDate;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    @OneToMany(mappedBy = "goal")
    public List<Subtask> subtasks = new ArrayList<>();
}

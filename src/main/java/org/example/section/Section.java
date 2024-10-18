package org.example.section;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.history.Goal;
import org.example.reflection.Reflection;
import org.example.streak.Streak;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "sections")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDateTime created;
    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    private List<Streak> streaks = new ArrayList<>();
    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    private List<Reflection> reflections = new ArrayList<>();
    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    private List<Goal> goals = new ArrayList<>();
}

package org.example.Streak;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.section.Section;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Streak {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @CreatedDate
    LocalDate startDate;
    int days;
    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;
}

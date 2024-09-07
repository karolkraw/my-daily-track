package org.example.streak;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StreakRepository extends JpaRepository <Streak, Long> {
    Streak findByName(String name);
    List<Streak> findBySection_Name(String sectionName);

    void deleteByName(String name);
}

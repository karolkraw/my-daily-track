package org.example.streak;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StreakRepository extends JpaRepository <Streak, Long> {
    Streak findByNameAndSection_Name(String name, String sectionName);
    List<Streak> findBySection_Name(String sectionName);
    void deleteByNameAndSection_Name(String name, String sectionName);
}

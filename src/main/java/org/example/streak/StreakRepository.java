package org.example.streak;

import org.example.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StreakRepository extends JpaRepository <Streak, Long> {
    Optional<Streak> findByNameAndSection_NameAndSection_User(String name, String sectionName, User user);
    List<Streak> findBySection_NameAndSection_User(String sectionName, User user);
    void deleteByNameAndSection_NameAndSection_User(String name, String sectionName, User user);
}

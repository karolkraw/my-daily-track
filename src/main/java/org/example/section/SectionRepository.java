package org.example.section;

import org.example.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SectionRepository extends JpaRepository<Section, Long> {
    List<Section> findByUser(User user);
    Optional<Section> findByNameAndUser(String name, User user);
    Optional<Section> findByNameAndUser_Username(String name, String username);
}

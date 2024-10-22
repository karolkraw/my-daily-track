package org.example.reflection;

import org.example.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReflectionRepository extends JpaRepository<Reflection, Long> {
    Long countBySection_NameAndSection_User(String sectionName, User user);
    Page<Reflection> findBySection_NameAndSection_UserAndCreatedNot(String sectionName, User user, LocalDate created, Pageable pageable);
    Optional<Reflection> findBySection_NameAndSection_UserAndCreated(String sectionName, User user, LocalDate created);
    Optional<Reflection> findFirstBySection_NameAndSection_UserAndCreatedAfterOrderByCreatedAsc(String sectionName, User user, LocalDate created);
    Optional<Reflection> findFirstBySection_NameAndSection_UserAndCreatedBeforeOrderByCreatedDesc(String sectionName, User user, LocalDate created);
    List<Reflection> findBySection_NameAndSection_UserAndCreatedAfterOrderByCreatedAsc(String sectionName, User user, LocalDate created, Pageable pageable);
    List<Reflection> findBySection_NameAndSection_UserAndCreatedBeforeOrderByCreatedDesc(String sectionName, User user, LocalDate created, Pageable pageable);
}

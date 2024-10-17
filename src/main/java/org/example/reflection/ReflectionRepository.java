package org.example.reflection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReflectionRepository extends JpaRepository<Reflection, Long> {
    Long countBySectionName(String sectionName);
    Page<Reflection> findBySection_NameAndCreatedNot(String sectionName, LocalDate created, Pageable pageable);
    Optional<Reflection> findBySection_NameAndCreated(String sectionName, LocalDate created);
    Optional<Reflection> findFirstBySection_NameAndCreatedAfterOrderByCreatedAsc(String sectionName, LocalDate created);
    Optional<Reflection> findFirstBySection_NameAndCreatedBeforeOrderByCreatedDesc(String sectionName, LocalDate created);
    List<Reflection> findBySection_NameAndCreatedAfterOrderByCreatedAsc(String sectionName, LocalDate created, Pageable pageable);
    List<Reflection> findBySection_NameAndCreatedBeforeOrderByCreatedDesc(String sectionName, LocalDate created, Pageable pageable);
}

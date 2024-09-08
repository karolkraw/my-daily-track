package org.example.reflection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ReflectionRepository extends JpaRepository<Reflection, Long> {
    Long countBySectionName(String sectionName);
    Page<Reflection> findBySection_Name(String sectionName, Pageable pageable);
    Optional<Reflection> findBySection_NameAndCreated(String sectionName, LocalDate created);
}

package org.example.section;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section, Long> {
    Section findByName(String sectionName);

    void deleteByName(String name);
}

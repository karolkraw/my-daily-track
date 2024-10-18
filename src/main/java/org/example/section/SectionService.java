package org.example.section;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SectionService {
    private final SectionRepository sectionRepository;
    SectionService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    Section createSection(Section section) {
        return sectionRepository.save(section);
    }

    List<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    public Section getSectionByName(String sectionName) {
        return sectionRepository.findByName(sectionName);
    }

    @Transactional
    void deleteSection(String name) {
        sectionRepository.deleteByName(name);
    }
}

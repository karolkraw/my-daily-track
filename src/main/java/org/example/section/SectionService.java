package org.example.section;

import org.example.section.exception.SectionAlreadyExistsException;
import org.example.user.User;
import org.example.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SectionService {
    private final SectionRepository sectionRepository;
    private final UserService userService;
    SectionService(SectionRepository sectionRepository, UserService userService) {
        this.sectionRepository = sectionRepository;
        this.userService = userService;
    }

    public Section createSection(Section section) {
        User currentUser = userService.getCurrentUser();
        Optional<Section> retrievedSection = sectionRepository.findByNameAndUser(section.getName(), currentUser);
        if (retrievedSection.isPresent()) {
            throw new SectionAlreadyExistsException("A section with this name already exists.");
        }
        section.setUser(currentUser);
        return sectionRepository.save(section);
    }

    public List<Section> getSections() {
        User currentUser = userService.getCurrentUser();
        return sectionRepository.findByUser(currentUser);
    }

    public Section getSectionByName(String sectionName) {
        User currentUser = userService.getCurrentUser();
        return sectionRepository.findByNameAndUser(sectionName, currentUser)
                .orElseThrow(() -> new RuntimeException("Section not found or does not belong to the current user"));
    }

    public Section getSectionByNameAndUsername(String sectionName, String username) {
        return sectionRepository.findByNameAndUser_Username(sectionName, username)
                .orElseThrow(() -> new RuntimeException("Section not found or does not belong to the current user"));
    }

    @Transactional
    public void deleteSection(String name) {
        User currentUser = userService.getCurrentUser();
        Section section = sectionRepository.findByNameAndUser(name, currentUser)
                .orElseThrow(() -> new RuntimeException("Section not found or does not belong to the current user"));
        sectionRepository.delete(section);
    }
}

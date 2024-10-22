package org.example.reflection;

import org.example.section.Section;
import org.example.section.SectionService;
import org.example.user.User;
import org.example.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.example.utils.DateUtils.convertStringToLocalDate;

@Service
public class ReflectionService {
    private final ReflectionRepository reflectionRepository;
    private final SectionService sectionService;
    private final UserService userService;

    ReflectionService(ReflectionRepository reflectionRepository, SectionService sectionService, UserService userService) {
        this.reflectionRepository = reflectionRepository;
        this.sectionService = sectionService;
        this.userService = userService;
    }
    public Reflection createReflection(Reflection reflection, String sectionName) {
        Section section = sectionService.getSectionByName(sectionName);
        reflection.setSection(section);
        return reflectionRepository.save(reflection);
    }

    public Long getReflectionCount(String sectionName) {
        User currentUser = userService.getCurrentUser();
        return reflectionRepository.countBySection_NameAndSection_User(sectionName, currentUser);
    }

    public Optional<Reflection> getReflectionByDate(String sectionName, String date) {
        User currentUser = userService.getCurrentUser();
        LocalDate created = convertStringToLocalDate(date);
        return reflectionRepository.findBySection_NameAndSection_UserAndCreated(sectionName, currentUser, created);
    }

    public List<Reflection> getReflections(String sectionName, int page, int pageSize) {
        User currentUser = userService.getCurrentUser();
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("created").descending());
        Page<Reflection> reflectionPage = reflectionRepository.findBySection_NameAndSection_UserAndCreatedNot(sectionName, currentUser, LocalDate.now(), pageable);
        return reflectionPage.getContent();
    }

    public Optional<Reflection> getNextReflection(String sectionName, String date) {
        User currentUser = userService.getCurrentUser();
        LocalDate created = convertStringToLocalDate(date);
        return reflectionRepository.findFirstBySection_NameAndSection_UserAndCreatedAfterOrderByCreatedAsc(sectionName, currentUser, created);
    }

    public Optional<Reflection> getPreviousReflection(String sectionName, String date) {
        User currentUser = userService.getCurrentUser();
        LocalDate created = convertStringToLocalDate(date);
        return reflectionRepository.findFirstBySection_NameAndSection_UserAndCreatedBeforeOrderByCreatedDesc(sectionName, currentUser, created);
    }

    public List<Reflection> getNextBatch(String sectionName, String date, int limit) {
        User currentUser = userService.getCurrentUser();
        LocalDate created = convertStringToLocalDate(date);
        return reflectionRepository.findBySection_NameAndSection_UserAndCreatedAfterOrderByCreatedAsc(sectionName, currentUser, created, PageRequest.of(0, limit));
    }

    public List<Reflection> getPreviousBatch(String sectionName, String date, int limit) {
        User currentUser = userService.getCurrentUser();
        LocalDate created = convertStringToLocalDate(date);
        return reflectionRepository.findBySection_NameAndSection_UserAndCreatedBeforeOrderByCreatedDesc(sectionName, currentUser, created, PageRequest.of(0, limit));
    }
}

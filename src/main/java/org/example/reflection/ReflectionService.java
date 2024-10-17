package org.example.reflection;

import org.example.section.Section;
import org.example.section.SectionService;
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

    ReflectionService(ReflectionRepository reflectionRepository, SectionService sectionService) {
        this.reflectionRepository = reflectionRepository;
        this.sectionService = sectionService;
    }
    Reflection createReflection(Reflection reflection, String sectionName) {
        Section section = sectionService.getSectionByName(sectionName);
        reflection.setSection(section);
        return reflectionRepository.save(reflection);
    }

    Long getReflectionCount(String sectionName) {
        return reflectionRepository.countBySectionName(sectionName);
    }

    Optional<Reflection> getReflectionByDate(String sectionName, String date) {
        LocalDate created = convertStringToLocalDate(date);
        return reflectionRepository.findBySection_NameAndCreated(sectionName, created);
    }

    List<Reflection> getReflections(String sectionName, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("created").descending());
        Page<Reflection> reflectionPage = reflectionRepository.findBySection_NameAndCreatedNot(sectionName, LocalDate.now(), pageable);
        return reflectionPage.getContent();
    }

    Optional<Reflection> getNextReflection(String sectionName, String date) {
        LocalDate created = convertStringToLocalDate(date);
        return reflectionRepository.findFirstBySection_NameAndCreatedAfterOrderByCreatedAsc(sectionName, created);
    }

    Optional<Reflection> getPreviousReflection(String sectionName, String date) {
        LocalDate created = convertStringToLocalDate(date);
        return reflectionRepository.findFirstBySection_NameAndCreatedBeforeOrderByCreatedDesc(sectionName, created);
    }

    List<Reflection> getNextBatch(String sectionName, String date, int limit) {
        LocalDate created = convertStringToLocalDate(date);
        return reflectionRepository.findBySection_NameAndCreatedAfterOrderByCreatedAsc(sectionName, created, PageRequest.of(0, limit));
    }

    List<Reflection> getPreviousBatch(String sectionName, String date, int limit) {
        LocalDate created = convertStringToLocalDate(date);
        return reflectionRepository.findBySection_NameAndCreatedBeforeOrderByCreatedDesc(sectionName, created, PageRequest.of(0, limit));
    }
}

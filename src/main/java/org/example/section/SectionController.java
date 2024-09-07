package org.example.section;

import org.example.section.dto.SectionDto;
import org.example.section.dto.SectionDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.section.dto.SectionDtoMapper.*;


@RestController
@RequestMapping("/api/sections")
public class SectionController {
    private final SectionService sectionService;
    SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @PostMapping
    public ResponseEntity<SectionDto> createSection(@RequestBody SectionDto sectionDto) {
        Section section = sectionService.createSection(mapDtoToSection(sectionDto));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapSectionToDto(section));
    }

    @GetMapping
    public ResponseEntity<List<SectionDto>> getAllSections() {
        List<SectionDto> sectionsDto = mapSectionsToDto(sectionService.getAllSections());
        return ResponseEntity
                .ok(sectionsDto);
    }
}

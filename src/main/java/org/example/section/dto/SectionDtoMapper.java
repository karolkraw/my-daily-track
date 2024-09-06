package org.example.section.dto;

import org.example.section.Section;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class SectionDtoMapper {
    public static SectionDto mapSectionToDto(Section section) {
        if (section == null) {
            return null;
        }
        return new SectionDto(section.getName());
    }

    public static List<SectionDto> mapSectionsToDto(List<Section> sections) {
        if (sections == null) {
            return null;
        }
        return sections.stream()
                .map(SectionDtoMapper::mapSectionToDto)
                .collect(Collectors.toList());
    }

    public static Section mapDtoToSection(SectionDto sectionDto) {
        if (sectionDto == null) {
            return null;
        }
        Section section = new Section();
        section.setName(sectionDto.getName());
        section.setCreated(LocalDateTime.now());
        return section;
    }
}

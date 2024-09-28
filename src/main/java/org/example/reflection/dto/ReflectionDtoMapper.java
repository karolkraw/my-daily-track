package org.example.reflection.dto;

import org.example.reflection.Reflection;

import java.util.List;
import java.util.stream.Collectors;

import static org.example.utils.DateUtils.convertLocalDateToString;
import static org.example.utils.DateUtils.convertStringToLocalDate;

public class ReflectionDtoMapper {
    public static ReflectionDto mapReflectionToDto(Reflection reflection) {
        if (reflection == null) {
            return null;
        }
        return new ReflectionDto(reflection.getContent(), convertLocalDateToString(reflection.getCreated()));
    }

    public static List<ReflectionDto> mapReflectionsToDto(List<Reflection> reflections) {
        if (reflections == null) {
            return null;
        }
        return reflections.stream()
                .map(ReflectionDtoMapper::mapReflectionToDto)
                .collect(Collectors.toList());
    }

    public static Reflection mapDtoToReflection(ReflectionDto reflectionDto) {
        if (reflectionDto == null) {
            return null;
        }
        Reflection reflection = new Reflection();
        reflection.setContent(reflectionDto.content);
        reflection.setCreated(convertStringToLocalDate(reflectionDto.created));
        return reflection;
    }
}

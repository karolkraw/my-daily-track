package org.example.reflection;

import org.example.reflection.dto.ReflectionDto;
import org.example.reflection.dto.ReflectionDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.example.reflection.dto.ReflectionDtoMapper.mapReflectionToDto;

@RestController
@RequestMapping("/api/reflections")
public class ReflectionController {
    private final ReflectionService reflectionService;
    public ReflectionController(ReflectionService reflectionService) {
        this.reflectionService = reflectionService;
    }

    @GetMapping("{sectionName}/{date}")
    ResponseEntity<ReflectionDto> getReflectionByDate(@PathVariable String sectionName, @PathVariable String date) {
        Optional<Reflection> reflection = reflectionService.getReflectionByDate(sectionName, date);
        if (reflection.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        ReflectionDto reflectionDto = mapReflectionToDto(reflection.get());
        return ResponseEntity.ok(reflectionDto);
    }

    @GetMapping("{sectionName}")
    ResponseEntity<List<ReflectionDto>> getReflections(@PathVariable String sectionName, @RequestParam int page, @RequestParam int pageSize) {
        List<Reflection> reflections = reflectionService.getReflections(sectionName, page, pageSize);
        List<ReflectionDto> reflectionsDto = reflections.stream().map(ReflectionDtoMapper::mapReflectionToDto).toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(reflectionsDto);
    }

    @GetMapping("{sectionName}/count")
    ResponseEntity<Long> getReflectionCount(@PathVariable String sectionName) {
        Long count = reflectionService.getReflectionCount(sectionName);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(count);
    }

    @PostMapping("{sectionName}")
    ResponseEntity<ReflectionDto> upsertReflection(@PathVariable String sectionName, @RequestBody ReflectionDto reflectionDto) {
        Optional<Reflection> existingReflection = reflectionService.getReflectionByDate(sectionName, reflectionDto.getCreated());

        Reflection savedReflection;
        if (existingReflection.isPresent()) {
            Reflection reflectionToUpdate = existingReflection.get();
            reflectionToUpdate.setContent(reflectionDto.getContent());
            savedReflection = reflectionService.createReflection(reflectionToUpdate, sectionName);
        } else {
            Reflection newReflection = ReflectionDtoMapper.mapDtoToReflection(reflectionDto);
            savedReflection = reflectionService.createReflection(newReflection, sectionName);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ReflectionDtoMapper.mapReflectionToDto(savedReflection));
    }

    @GetMapping("{sectionName}/by-date")
    public ResponseEntity<ReflectionDto> getReflectionByDateSearch(@PathVariable String sectionName, @RequestParam("date") String date) {
        Optional<Reflection> reflection = reflectionService.getReflectionByDate(sectionName, date);
        if (reflection.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        ReflectionDto reflectionDto = mapReflectionToDto(reflection.get());
        return ResponseEntity.ok(reflectionDto);
    }

    @GetMapping("{sectionName}/next")
    public ResponseEntity<ReflectionDto> getNextReflection(@PathVariable String sectionName, @RequestParam("date") String date) {
        Optional<Reflection> reflection = reflectionService.getNextReflection(sectionName, date);
        if (reflection.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        ReflectionDto reflectionDto = mapReflectionToDto(reflection.get());
        return ResponseEntity.ok(reflectionDto);
    }

    @GetMapping("{sectionName}/previous")
    public ResponseEntity<ReflectionDto> getPreviousReflection(@PathVariable String sectionName, @RequestParam("date") String date) {
        Optional<Reflection> reflection = reflectionService.getPreviousReflection(sectionName, date);
        if (reflection.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        ReflectionDto reflectionDto = mapReflectionToDto(reflection.get());
        return ResponseEntity.ok(reflectionDto);
    }

    @GetMapping("{sectionName}/next-batch")
    public ResponseEntity<List<ReflectionDto>> getNextBatch(@PathVariable String sectionName, @RequestParam("date") String date, @RequestParam("limit") int limit) {
        List<Reflection> reflections = reflectionService.getNextBatch(sectionName, date, limit);
        List<ReflectionDto> reflectionsDto = reflections.stream().map(ReflectionDtoMapper::mapReflectionToDto).toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(reflectionsDto);
    }

    @GetMapping("{sectionName}/previous-batch")
    public ResponseEntity<List<ReflectionDto>> getPreviousBatch(@PathVariable String sectionName, @RequestParam("date") String date, @RequestParam("limit") int limit) {
        List<Reflection> reflections = reflectionService.getPreviousBatch(sectionName, date, limit);
        List<ReflectionDto> reflectionsDto = reflections.stream().map(ReflectionDtoMapper::mapReflectionToDto).toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(reflectionsDto);
    }
}



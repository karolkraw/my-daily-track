package org.example.section.exception;

public class SectionAlreadyExistsException extends RuntimeException {
    public SectionAlreadyExistsException(String message) {
        super(message);
    }
}

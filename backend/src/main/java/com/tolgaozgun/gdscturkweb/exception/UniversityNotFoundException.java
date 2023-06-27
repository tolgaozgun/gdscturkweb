package com.tolgaozgun.gdscturkweb.exception;

public class UniversityNotFoundException extends RuntimeException {
    public UniversityNotFoundException() {
        super("University not found!");
    }
}

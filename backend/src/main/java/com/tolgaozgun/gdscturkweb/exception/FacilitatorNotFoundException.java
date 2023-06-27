package com.tolgaozgun.gdscturkweb.exception;

public class FacilitatorNotFoundException extends RuntimeException {
    public FacilitatorNotFoundException() {
        super("Facilitator not found!");
    }
}

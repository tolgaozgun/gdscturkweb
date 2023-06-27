package com.tolgaozgun.gdscturkweb.exception;

public class LeadNotFoundException extends RuntimeException {
    public LeadNotFoundException() {
        super("Lead not found!");
    }
}

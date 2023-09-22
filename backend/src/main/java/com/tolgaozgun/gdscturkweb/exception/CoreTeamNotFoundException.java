package com.tolgaozgun.gdscturkweb.exception;

public class CoreTeamNotFoundException extends RuntimeException {
    public CoreTeamNotFoundException() {
        super("Core team not found!");
    }
}

package com.tolgaozgun.gdscturkweb.exception;

public class BuddyTeamNotFoundException extends RuntimeException {
    public BuddyTeamNotFoundException() {
        super("Buddy team not found!");
    }
}

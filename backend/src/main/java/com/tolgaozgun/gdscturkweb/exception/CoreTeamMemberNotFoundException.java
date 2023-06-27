package com.tolgaozgun.gdscturkweb.exception;

public class CoreTeamMemberNotFoundException extends RuntimeException {
    public CoreTeamMemberNotFoundException() {
        super("Core team member not found!");
    }
}

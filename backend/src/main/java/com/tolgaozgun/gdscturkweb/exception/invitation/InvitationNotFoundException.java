package com.tolgaozgun.gdscturkweb.exception.invitation;

public class InvitationNotFoundException extends RuntimeException {
    public InvitationNotFoundException() {
        super("Invitation not found!");
    }

}
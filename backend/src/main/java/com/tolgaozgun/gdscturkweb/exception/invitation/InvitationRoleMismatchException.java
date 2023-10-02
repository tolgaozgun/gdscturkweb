package com.tolgaozgun.gdscturkweb.exception.invitation;

import com.tolgaozgun.gdscturkweb.enums.UserType;

public class InvitationRoleMismatchException extends RuntimeException {
    public InvitationRoleMismatchException() {
        super("The invitation role and the user role are not the same!");
    }

    public InvitationRoleMismatchException(UserType invitationRole, UserType userRole) {
        super("The invitation role and the user role are not the same! Invitation role: " + invitationRole + " User role: " + userRole);
    }

}
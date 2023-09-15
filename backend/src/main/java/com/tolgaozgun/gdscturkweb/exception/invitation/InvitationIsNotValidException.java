package com.tolgaozgun.gdscturkweb.exception.invitation;

import com.tolgaozgun.gdscturkweb.enums.UserType;

public class InvitationIsNotValidException extends RuntimeException {
    public InvitationIsNotValidException() {
        super("The invitation is not valid!");
    }

}
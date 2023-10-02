package com.tolgaozgun.gdscturkweb.exception.invitation;

import java.util.Date;

public class InvitationIsExpiredException extends RuntimeException {
    public InvitationIsExpiredException() {
        super("The invitation has expired!");
    }

    public InvitationIsExpiredException(Date expirationDate) {
        super("The invitation has expired! Expiration date: " + expirationDate);
    }

}
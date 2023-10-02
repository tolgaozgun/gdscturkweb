package com.tolgaozgun.gdscturkweb.exception.verification;

import java.util.Date;

public class EmailVerificationExpiredException extends RuntimeException {
    public EmailVerificationExpiredException() {
        super("Email verification expired!");
    }

    public EmailVerificationExpiredException(Date date) {
        super("Email verification expired at " + date.toString());
    }
}
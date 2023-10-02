package com.tolgaozgun.gdscturkweb.exception;

public class EmailVerificationNotFoundException extends RuntimeException {
    public EmailVerificationNotFoundException() {
        super("Email verification is not found!");
    }

}
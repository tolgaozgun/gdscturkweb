package com.tolgaozgun.gdscturkweb.exception.verification;

public class EmailVerificationAlreadyUsedException extends RuntimeException {
    public EmailVerificationAlreadyUsedException() {
        super("This email verification is already used!");
    }

}
package com.tolgaozgun.gdscturkweb.exception.verification;

public class EmailAlreadyVerifiedException extends RuntimeException {
    public EmailAlreadyVerifiedException() {
        super("Email is already verified!");
    }
}
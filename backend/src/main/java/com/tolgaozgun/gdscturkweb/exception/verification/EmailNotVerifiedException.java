package com.tolgaozgun.gdscturkweb.exception.verification;

public class EmailNotVerifiedException extends RuntimeException {
    public EmailNotVerifiedException() {
        super("Email is not verified!");
    }
}
package com.tolgaozgun.gdscturkweb.exception.verification;

public class UserAlreadyVerifiedException extends RuntimeException {
    public UserAlreadyVerifiedException() {
        super("User is already verified!");
    }
}
package com.tolgaozgun.gdscturkweb.exception.verification;

public class UserNotVerifiedException extends RuntimeException {
    public UserNotVerifiedException() {
        super("User is not verified!");
    }
}

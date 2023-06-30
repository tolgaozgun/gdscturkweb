package com.tolgaozgun.gdscturkweb.exception.verification;


public class UserAlreadyUnverifiedException extends RuntimeException {
    public UserAlreadyUnverifiedException() {
        super("User is  already unverified!");
    }
}
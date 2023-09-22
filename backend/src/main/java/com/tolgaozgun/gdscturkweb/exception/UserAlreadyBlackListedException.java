package com.tolgaozgun.gdscturkweb.exception;

public class UserAlreadyBlackListedException extends RuntimeException {
    public UserAlreadyBlackListedException() {
        super("User is already blacklisted!");
    }

}

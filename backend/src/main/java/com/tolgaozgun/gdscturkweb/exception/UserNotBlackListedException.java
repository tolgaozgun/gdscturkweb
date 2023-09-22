package com.tolgaozgun.gdscturkweb.exception;

public class UserNotBlackListedException extends RuntimeException {
    public UserNotBlackListedException() {
        super("User is not blacklisted!");
    }

}

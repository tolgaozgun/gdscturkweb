package com.tolgaozgun.gdscturkweb.exception;

public class UserTypeNotFoundException extends RuntimeException {
    public UserTypeNotFoundException() {
        super("Error while getting user type details");
    }
}

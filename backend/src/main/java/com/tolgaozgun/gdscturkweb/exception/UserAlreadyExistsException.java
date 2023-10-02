package com.tolgaozgun.gdscturkweb.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("This user already exists!");
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}

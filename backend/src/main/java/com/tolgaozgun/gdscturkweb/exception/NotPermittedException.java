package com.tolgaozgun.gdscturkweb.exception;

public class NotPermittedException  extends RuntimeException {
    public NotPermittedException() {
        super("You are not permitted to do this action!");
    }

}

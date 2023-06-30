package com.tolgaozgun.gdscturkweb.exception;

public class GooglerNotFoundException extends RuntimeException {
    public GooglerNotFoundException() {
        super("Googler not found!");
    }
}

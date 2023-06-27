package com.tolgaozgun.gdscturkweb.exception;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException() {
        super("City not found!");
    }
}

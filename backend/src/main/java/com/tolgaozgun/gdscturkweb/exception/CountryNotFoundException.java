package com.tolgaozgun.gdscturkweb.exception;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException() {
        super("Country not found!");
    }
}

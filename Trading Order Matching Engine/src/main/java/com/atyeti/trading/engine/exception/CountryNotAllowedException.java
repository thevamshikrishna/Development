package com.atyeti.trading.engine.exception;

public class CountryNotAllowedException extends RuntimeException {
    public CountryNotAllowedException(String message) {
        super(message);
    }
}

package com.atyeti.exception;

public class UnableToConnectDataBase extends RuntimeException {
    public UnableToConnectDataBase(String message) {
        super(message);
    }
}

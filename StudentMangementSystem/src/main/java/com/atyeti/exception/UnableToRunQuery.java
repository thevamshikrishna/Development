package com.atyeti.exception;

public class UnableToRunQuery extends RuntimeException {
    public UnableToRunQuery(String message) {
        super(message);
    }
}

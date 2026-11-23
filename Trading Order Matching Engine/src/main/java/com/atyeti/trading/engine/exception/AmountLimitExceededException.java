package com.atyeti.trading.engine.exception;

public class AmountLimitExceededException extends RuntimeException {
    public AmountLimitExceededException(String message) {
        super(message);
    }
}

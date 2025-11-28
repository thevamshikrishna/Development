package com.atyeti.trading.engine.service.validation;

import com.atyeti.trading.engine.exception.InvalidOrderException;
import com.atyeti.trading.engine.model.Order;

public interface OrderValidator {
    void validate(Order order) throws InvalidOrderException;
}

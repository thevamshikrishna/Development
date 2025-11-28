package com.atyeti.trading.engine.service.validation;

import com.atyeti.trading.engine.model.Order;

import java.util.ArrayList;
import java.util.List;

public class CompositeValidator implements OrderValidator{
    private final List<OrderValidator> validators = new ArrayList<>();

    public CompositeValidator() {
        validators.add(new BasicDataValidator());
        validators.add(new CountryValidator());
        validators.add(new AmountValidator());
    }

    @Override
    public void validate(Order order) {
        for (OrderValidator v : validators) {
            v.validate(order);
        }
    }
}

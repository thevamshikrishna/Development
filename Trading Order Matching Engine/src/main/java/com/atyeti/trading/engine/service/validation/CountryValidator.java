package com.atyeti.trading.engine.service.validation;

import com.atyeti.trading.engine.exception.CountryNotAllowedException;
import com.atyeti.trading.engine.model.Order;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CountryValidator implements OrderValidator {
    private static final Set<String> APPROVED = new HashSet<>(Arrays.asList("US","UK","IN","SG","JP","DE","FR"));

    @Override
    public void validate(Order order) {
        if (order.getCountryCode() == null || !APPROVED.contains(order.getCountryCode().toUpperCase())) {
            throw new CountryNotAllowedException("Country not allowed: " + order.getCountryCode());
        }
    }
}

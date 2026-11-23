package com.atyeti.trading.engine.service.validation;

import com.atyeti.trading.engine.exception.InvalidOrderException;
import com.atyeti.trading.engine.model.Order;

public class BasicDataValidator implements OrderValidator {
    @Override
    public void validate(Order order) {
        if (order.getOrderId() == null || order.getOrderId().trim().isEmpty()) {
            throw new InvalidOrderException("OrderId missing");
        }
        if (order.getTraderId() == null || order.getTraderId().trim().isEmpty()) {
            throw new InvalidOrderException("TraderId missing");
        }
        if (order.getPrice() <= 0) {
            throw new InvalidOrderException("Price must be > 0");
        }
        if (order.getQuantity() <= 0) {
            throw new InvalidOrderException("Quantity must be > 0");
        }
        if (order.getTradeType() == null) {
            throw new InvalidOrderException("TradeType missing");
        }
        if (order.getOrderType() == null) {
            throw new InvalidOrderException("OrderType missing");
        }
    }
}

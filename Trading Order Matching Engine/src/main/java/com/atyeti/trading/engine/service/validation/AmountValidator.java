package com.atyeti.trading.engine.service.validation;

import com.atyeti.trading.engine.exception.AmountLimitExceededException;
import com.atyeti.trading.engine.model.Order;
import com.atyeti.trading.engine.model.TradeType;

public class AmountValidator implements OrderValidator {

    @Override
    public void validate(Order order) {
        double amount = order.getPrice() * order.getQuantity();
        TradeType t = order.getTradeType();
        if (t == TradeType.EQUITY && amount > 100_000) {
            throw new AmountLimitExceededException("EQUITY max 100,000. Amount: " + amount);
        } else if (t == TradeType.FOREX && amount > 500_000) {
            throw new AmountLimitExceededException("FOREX max 500,000. Amount: " + amount);
        } else if (t == TradeType.CRYPTO && amount > 50_000) {
            throw new AmountLimitExceededException("CRYPTO max 50,000. Amount: " + amount);
        }
    }
}

package service.validator;

import exception.AmountLimitExceededException;
import exception.InvalidOrderException;
import model.Order;
import model.TradeType;

public class AmountValidator implements OrderValidatorService{
    @Override
    public void validate(Order order){
        double amount=order.getPrice()* order.getQuantity();

        TradeType t=order.getTradeType();

        if (t == TradeType.EQUITY && amount > 100000) {
            throw new AmountLimitExceededException("EQUITY max 100,000 Amount: " + amount);
        } else if (t == TradeType.FOREX && amount > 500000) {
            throw new AmountLimitExceededException("FOREX max 500,000 Amount: " + amount);
        } else if (t == TradeType.CRYPTO && amount > 50000) {
            throw new AmountLimitExceededException("CRYPTO max 50,000 Amount: " + amount);
        }
    }
}

package service.validator;

import exception.InvalidOrderException;
import model.Order;

public class DataValidator implements OrderValidatorService{
    @Override
    public void validate(Order order){
        if(order.getOrderType()==null){
            throw new InvalidOrderException("TradeType missing");
        }

        if(order.getOrderId()==null || order.getOrderId().trim().isEmpty()){
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
    }


}

package service.validator;

import exception.AmountLimitExceededException;
import exception.CountryNotAllowedException;
import exception.InvalidOrderException;
import model.Order;
import model.TradeType;

import java.util.Set;

public class OrderValidationEngine implements OrderValidatorService {

    private static final Set<String> APPROVED_COUNTRIES = Set.of("US", "UK", "IN", "SG", "JP", "DE", "FR");

    private static final double MAX_EQUITY_VALUE = 100_000;
    private static final double MAX_FOREX_VALUE = 500_000;
    private static final double MAX_CRYPTO_VALUE = 50_000;

    @Override
    public void validate(Order order) {

        if (order.getOrderId() == null || order.getOrderId().isBlank())
            throw new InvalidOrderException("Order ID cannot be null or empty");

        if (order.getTraderId() == null || order.getTraderId().isBlank())
            throw new InvalidOrderException("Trader ID required");

        if (order.getTradeType() == null)
            throw new InvalidOrderException("Trade type required");

        if (order.getOrderType() == null)
            throw new InvalidOrderException("Order type required");

        if (order.getPrice() <= 0)
            throw new InvalidOrderException("Price must be positive");

        if (order.getQuantity() <= 0)
            throw new InvalidOrderException("Quantity must be positive");

        if (order.getCountry() == null || !APPROVED_COUNTRIES.contains(order.getCountry().toUpperCase()))
            throw new CountryNotAllowedException("Country not approved: " + order.getCountry());

        double value = order.getPrice() * order.getQuantity();

        if (order.getTradeType() == TradeType.EQUITY && value > MAX_EQUITY_VALUE)
            throw new AmountLimitExceededException("Equity order exceeds $100,000 limit");

        if (order.getTradeType() == TradeType.FOREX && value > MAX_FOREX_VALUE)
            throw new AmountLimitExceededException("Forex order exceeds $500,000 limit");

        if (order.getTradeType() == TradeType.CRYPTO && value > MAX_CRYPTO_VALUE)
            throw new AmountLimitExceededException("Crypto order exceeds $50,000 limit");
    }

}
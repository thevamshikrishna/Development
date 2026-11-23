package service;

import exception.OrderException;
import inmemory.OrderBook;
import model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.matching.Matching;
import service.matching.MatchingService;
import service.validator.OrderValidationEngine;
import service.validator.OrderValidatorService;
import inmemory.SimpleOrderBook;
import inmemory.InMemoryTrade;

import java.sql.Timestamp;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    // DIP: depend on abstractions
    private final OrderBook orderBook;
    private final InMemoryTrade tradeBook;
    private final Matching matchingStrategy;
    private final OrderValidatorService validator;

    private int totalOrdersLoaded = 0;
    private int rejectedOrders = 0;
    private int validOrders = 0;

    // For duplicate order ID protection
    private final Set<String> seenOrderIds = ConcurrentHashMap.newKeySet();

    /**
     * Zero-arg constructor for simple usage.
     */
    public OrderService() {
        this(
                new SimpleOrderBook(),
                new InMemoryTrade(),
                null, // will be created automatically
                new OrderValidationEngine()
        );
    }

    /**
     * Main constructor for dependency injection.
     */
    public OrderService(OrderBook orderBook,
                        InMemoryTrade tradeBook,
                        Matching matchingStrategy,
                        OrderValidatorService validator) {

        this.orderBook = orderBook;
        this.tradeBook = tradeBook;
        this.validator = validator;

        // If no matchingStrategy provided, create default
        if (matchingStrategy == null) {
            this.matchingStrategy = new MatchingService(orderBook, tradeBook);
        } else {
            this.matchingStrategy = matchingStrategy;
        }
    }

    // Main public API
    public synchronized Order submitOrder(String orderId, String traderId, TradeType tradeType,
                                          OrderType orderType, double price, long quantity,
                                          String country, Timestamp timestamp) {

        incrementTotalOrders();

        // Duplicate order ID protection
        if (!seenOrderIds.add(orderId)) {
            incrementRejectedOrders();
            log.warn("DUPLICATE ORDER ID REJECTED: {}", orderId);
            Order rejected = new Order();
            rejected.setStatus(OrderStatus.REJECTED);
            return rejected;
        }

        Order order = new Order(orderId, traderId, tradeType, orderType, price, quantity, country, timestamp);

        try {
            validator.validate(order);
            incrementValidOrders();

            matchingStrategy.match(order);
            return order;

        } catch (OrderException e) {
            incrementRejectedOrders();
            order.setStatus(OrderStatus.REJECTED);
            log.warn("REJECTED {}: {}", orderId, e.getMessage());
            return order;
        }
    }

    // CsvReader uses this
    public Order submitCsvOrder(String orderId, String traderId, TradeType tradeType,
                                OrderType orderType, double price, long quantity,
                                String country, Timestamp timestamp) {
        return submitOrder(orderId, traderId, tradeType, orderType, price, quantity, country, timestamp);
    }

    // Counter helpers
    private void incrementTotalOrders()   { totalOrdersLoaded++; }
    private void incrementRejectedOrders(){ rejectedOrders++; }
    private void incrementValidOrders()   { validOrders++; }

    // Getters
    public int getTotalOrdersLoaded() { return totalOrdersLoaded; }
    public int getRejectedOrders()    { return rejectedOrders; }
    public int getValidOrders()       { return validOrders; }

    public OrderBook getOrderBook()          { return orderBook; }
    public InMemoryTrade getTradeRepository() { return tradeBook; }
}
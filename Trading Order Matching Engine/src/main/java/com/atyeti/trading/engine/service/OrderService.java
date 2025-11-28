package com.atyeti.trading.engine.service;

import com.atyeti.trading.engine.concurrency.LockManager;
import com.atyeti.trading.engine.model.Order;
import com.atyeti.trading.engine.model.OrderType;
import com.atyeti.trading.engine.model.TradeType;
import com.atyeti.trading.engine.repository_InMemory.InMemoryOrderBookRepository;
import com.atyeti.trading.engine.repository_InMemory.InMemoryTradeRepository;
import com.atyeti.trading.engine.repository_InMemory.OrderBookRepository;
import com.atyeti.trading.engine.repository_InMemory.TradeRepository;
import com.atyeti.trading.engine.service.matching.DefaultMatchingStrategy;
import com.atyeti.trading.engine.service.matching.MatchingStrategy;
import com.atyeti.trading.engine.service.validation.CompositeValidator;
import com.atyeti.trading.engine.service.validation.OrderValidator;
import com.atyeti.trading.engine.util.IdGenerator;

import java.time.Instant;

public class OrderService {

    private final OrderBookRepository orderBookRepository;
    private final TradeRepository tradeRepository;
    private final MatchingStrategy matchingStrategy;
    private final OrderValidator validator;

    private int totalOrdersLoaded = 0;
    private int rejectedOrders = 0;
    private int validOrders = 0;

    public OrderService() {
        this.orderBookRepository = new InMemoryOrderBookRepository();
        this.tradeRepository = new InMemoryTradeRepository();
        this.validator = new CompositeValidator();
        this.matchingStrategy = new DefaultMatchingStrategy(orderBookRepository, tradeRepository, new LockManager());
    }

    public OrderService(OrderBookRepository orderBookRepository,
                        TradeRepository tradeRepository,
                        MatchingStrategy matchingStrategy,
                        OrderValidator validator) {
        this.orderBookRepository = orderBookRepository;
        this.tradeRepository = tradeRepository;
        this.matchingStrategy = matchingStrategy;
        this.validator = validator;
    }

    // === COUNTERS ===
    public int getTotalOrdersLoaded() { return totalOrdersLoaded; }
    public int getRejectedOrders() { return rejectedOrders; }
    public int getValidOrders() { return validOrders; }

    public void incrementTotalOrders() { totalOrdersLoaded++; }
    public void incrementRejectedOrders() { rejectedOrders++; }
    public void incrementValidOrders() { validOrders++; }


    // ================================================================
    // MANUAL ORDER (Generated ID)
    // ================================================================
    public Order submitOrder(String traderId,
                             TradeType tradeType,
                             OrderType orderType,
                             double price,
                             long quantity,
                             String country) {

        incrementTotalOrders();

        try {
            String orderId = IdGenerator.generateOrderId();
            Order order = new Order(orderId, traderId, tradeType, orderType, price, quantity, country, Instant.now());

            // validate
            validator.validate(order);

            // mark valid
            incrementValidOrders();

            // save
            orderBookRepository.saveOrder(order);

            // match
            matchingStrategy.match(order);

            return orderBookRepository.getOrderById(order.getOrderId());
        }
        catch (Exception ex) {
            incrementRejectedOrders();
            throw ex;  // let caller handle/log it
        }
    }


    // ================================================================
    // CSV ORDER (CSV-provided ID + timestamp)
    // ================================================================
    public Order submitCsvOrder(String orderId,
                                String traderId,
                                TradeType tradeType,
                                OrderType orderType,
                                double price,
                                long quantity,
                                String country,
                                Instant timestamp) {

        incrementTotalOrders();

        try {
            Order order = new Order(orderId, traderId, tradeType, orderType, price, quantity, country, timestamp);

            // validate
            validator.validate(order);

            // valid
            incrementValidOrders();

            // save
            orderBookRepository.saveOrder(order);

            // match
            matchingStrategy.match(order);

            return order;
        }
        catch (Exception ex) {
            incrementRejectedOrders();
            throw ex;
        }
    }

    public TradeRepository getTradeRepository() {
        return tradeRepository;
    }

    public OrderBookRepository getOrderBookRepository() {
        return orderBookRepository;
    }
}

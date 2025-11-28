package com.atyeti.trading.engine.repository_InMemory;

import com.atyeti.trading.engine.model.Order;
import com.atyeti.trading.engine.model.TradeType;

import java.util.Collection;

public interface OrderBookRepository {

    void saveOrder(Order order);

    Order getOrderById(String orderId);

    Collection<Order> getBuyOrders(TradeType tradeType);

    Collection<Order> getSellOrders(TradeType tradeType);

    // ADD THIS:
    Collection<Order> getAllOrders();

    OrderBook getOrderBook(TradeType tradeType);
}
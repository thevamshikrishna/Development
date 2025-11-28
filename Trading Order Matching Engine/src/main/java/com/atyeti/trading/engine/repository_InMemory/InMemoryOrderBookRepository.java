package com.atyeti.trading.engine.repository_InMemory;

import com.atyeti.trading.engine.model.Order;
import com.atyeti.trading.engine.model.OrderType;
import com.atyeti.trading.engine.model.TradeType;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryOrderBookRepository implements OrderBookRepository {

    private final Map<TradeType, OrderBook> books = new ConcurrentHashMap<>();
    private final Map<String, Order> orders = new ConcurrentHashMap<>();

    public InMemoryOrderBookRepository() {
        for (TradeType t : TradeType.values()) {
            books.put(t, new OrderBook());
        }
    }

    @Override
    public OrderBook getOrderBook(TradeType tradeType) {
        return books.get(tradeType);
    }

    @Override
    public void saveOrder(Order order) {

        // Store globally so summary can find them
        orders.put(order.getOrderId(), order);

        // Store inside relevant queue
        OrderBook book = books.get(order.getTradeType());

        if (order.getOrderType() == OrderType.BUY) {
            book.getBuyQueue().add(order);
        } else {
            book.getSellQueue().add(order);
        }
    }

    @Override
    public Order getOrderById(String orderId) {
        return orders.get(orderId);
    }

    @Override
    public Collection<Order> getBuyOrders(TradeType tradeType) {
        return Collections.unmodifiableCollection(
                books.get(tradeType).getBuyQueue()
        );
    }

    @Override
    public Collection<Order> getSellOrders(TradeType tradeType) {
        return Collections.unmodifiableCollection(
                books.get(tradeType).getSellQueue()
        );
    }

    @Override
    public Collection<Order> getAllOrders() {
        return Collections.unmodifiableCollection(orders.values());
    }
}
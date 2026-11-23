package com.atyeti.trading.engine.repository_InMemory;

import com.atyeti.trading.engine.model.Order;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class OrderBook {
    private final Queue<Order> buyQueue;
    private final Queue<Order> sellQueue;

    public OrderBook() {
        buyQueue = new PriorityQueue<>(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {

                // higher price first
                int p = Double.compare(o2.getPrice(), o1.getPrice());
                if (p != 0) return p;
                // earlier time first
                return o1.getTimestamp().compareTo(o2.getTimestamp());
            }
        });

        sellQueue = new PriorityQueue<>(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {

                // lower price first
                int p = Double.compare(o1.getPrice(), o2.getPrice());
                if (p != 0) return p;
                // earlier time first
                return o1.getTimestamp().compareTo(o2.getTimestamp());
            }
        });
    }

    public Queue<Order> getBuyQueue() { return buyQueue; }
    public Queue<Order> getSellQueue() { return sellQueue; }
}

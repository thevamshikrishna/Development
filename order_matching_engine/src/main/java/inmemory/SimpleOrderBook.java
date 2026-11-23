package inmemory;

import model.Order;
import model.OrderType;
import model.TradeType;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.PriorityBlockingQueue;

public class SimpleOrderBook implements OrderBook {

    private final Map<TradeType, OrderBookSide> books = new ConcurrentHashMap<>();
    private final List<Order> allSubmittedOrders = new CopyOnWriteArrayList<>();

    public static class OrderBookSide {

        public final PriorityBlockingQueue<Order> buyQueue =
                new PriorityBlockingQueue<>(100,
                        Comparator.comparingDouble(Order::getPrice).reversed()
                                .thenComparing(Order::getTimestamp));

        public final PriorityBlockingQueue<Order> sellQueue =
                new PriorityBlockingQueue<>(100,
                        Comparator.comparingDouble(Order::getPrice)
                                .thenComparing(Order::getTimestamp));
    }

    private OrderBookSide getBookSide(TradeType tradeType) {
        return books.computeIfAbsent(tradeType, k -> new OrderBookSide());
    }

    @Override
    public PriorityBlockingQueue<Order> getBuyQueue(TradeType tradeType) {
        return getBookSide(tradeType).buyQueue;
    }

    @Override
    public PriorityBlockingQueue<Order> getSellQueue(TradeType tradeType) {
        return getBookSide(tradeType).sellQueue;
    }

    @Override
    public void addOrder(Order order) {
        allSubmittedOrders.add(order);

        if (order.getOrderType() == OrderType.BUY) {
            getBuyQueue(order.getTradeType()).add(order);
        } else {
            getSellQueue(order.getTradeType()).add(order);
        }
    }

    @Override
    public List<Order> getAllOrders() {
        return Collections.unmodifiableList(new ArrayList<>(allSubmittedOrders));
    }
}

package service.matching;

import inmemory.InMemoryTrade;
import inmemory.OrderBook;
import model.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;

public class MatchingService implements Matching {

    private final OrderBook orderBook;
    private final InMemoryTrade tradeBook;

    private final Map<TradeType, Object> locks = new ConcurrentHashMap<>();

    public MatchingService(OrderBook orderBook, InMemoryTrade tradeBook) {
        this.orderBook = orderBook;
        this.tradeBook = tradeBook;

        for (TradeType type : TradeType.values()) {
            locks.put(type, new Object());
        }
    }

    @Override
    public void match(Order incoming) {

        synchronized (locks.get(incoming.getTradeType())) {

            if (incoming.getRemainingQuantity() <= 0) return;

            PriorityBlockingQueue<Order> oppositeQueue =
                    incoming.getOrderType() == OrderType.BUY
                            ? orderBook.getSellQueue(incoming.getTradeType())
                            : orderBook.getBuyQueue(incoming.getTradeType());

            while (incoming.getRemainingQuantity() > 0 && !oppositeQueue.isEmpty()) {

                Order topOpp = oppositeQueue.poll();

                boolean priceMatch = incoming.getOrderType() == OrderType.BUY
                        ? incoming.getPrice() >= topOpp.getPrice()
                        : incoming.getPrice() <= topOpp.getPrice();

                if (!priceMatch) {
                    oppositeQueue.add(topOpp);
                    break;
                }

                long matchQty = Math.min(
                        incoming.getRemainingQuantity(),
                        topOpp.getRemainingQuantity()
                );

                double execPrice = topOpp.getTimestamp().before(incoming.getTimestamp())
                        ? topOpp.getPrice()
                        : incoming.getPrice();

                Trade trade = new Trade(
                        tradeBook.generateTradeId(),
                        incoming.getOrderType() == OrderType.BUY
                                ? incoming.getOrderId()
                                : topOpp.getOrderId(),
                        incoming.getOrderType() == OrderType.BUY
                                ? topOpp.getOrderId()
                                : incoming.getOrderId(),
                        execPrice,
                        matchQty,
                        Timestamp.from(Instant.now()),
                        incoming.getTradeType()
                );

                tradeBook.saveTrade(trade);

                incoming.setRemainingQuantity(incoming.getRemainingQuantity() - matchQty);
                topOpp.setRemainingQuantity(topOpp.getRemainingQuantity() - matchQty);

                updateStatus(incoming);
                updateStatus(topOpp);

                if (topOpp.getRemainingQuantity() > 0) {
                    oppositeQueue.add(topOpp);
                }
            }

            updateStatus(incoming);

            if (incoming.getRemainingQuantity() > 0) {
                orderBook.addOrder(incoming);
            }
        }
    }

    // ⭐ Correctly placed here — NOT inside match()
    private void updateStatus(Order order) {
        if (order.getRemainingQuantity() <= 0) {
            order.setStatus(OrderStatus.FILLED);
        } else if (order.getRemainingQuantity() < order.getQuantity()) {
            order.setStatus(OrderStatus.PARTIALLY_FILLED);
        } else {
            order.setStatus(OrderStatus.PENDING);
        }
    }
}
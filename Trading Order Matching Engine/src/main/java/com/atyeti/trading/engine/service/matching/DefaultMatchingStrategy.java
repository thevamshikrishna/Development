package com.atyeti.trading.engine.service.matching;

import com.atyeti.trading.engine.concurrency.LockManager;
import com.atyeti.trading.engine.model.Order;
import com.atyeti.trading.engine.model.OrderType;
import com.atyeti.trading.engine.model.Trade;
import com.atyeti.trading.engine.repository_InMemory.OrderBook;
import com.atyeti.trading.engine.repository_InMemory.OrderBookRepository;
import com.atyeti.trading.engine.repository_InMemory.TradeRepository;
import com.atyeti.trading.engine.service.matching.MatchingStrategy;
import com.atyeti.trading.engine.util.IdGenerator;

import java.time.Instant;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Default matching strategy: price-time priority with partial fills allowed.
 */
public class DefaultMatchingStrategy implements MatchingStrategy {

    private final OrderBookRepository orderBookRepository;
    private final TradeRepository tradeRepository;
    private final LockManager lockManager;

    public DefaultMatchingStrategy(OrderBookRepository orderBookRepository,
                                   TradeRepository tradeRepository,
                                   LockManager lockManager) {
        this.orderBookRepository = orderBookRepository;
        this.tradeRepository = tradeRepository;
        this.lockManager = lockManager;
    }

    @Override
    public void match(Order incoming) {
        // Lock per trade type
        ReentrantLock lock = lockManager.getLock(incoming.getTradeType());
        lock.lock();
        try {
            OrderBook book = orderBookRepository.getOrderBook(incoming.getTradeType());
            Queue<Order> myQueue = (incoming.getOrderType() == OrderType.BUY) ? book.getBuyQueue() : book.getSellQueue();
            Queue<Order> oppQueue = (incoming.getOrderType() == OrderType.BUY) ? book.getSellQueue() : book.getBuyQueue();

            // If incoming was already added to repository, it will be in the queue. If not, we should add it (OrderService will add before match typically)
            // Try to match while incoming has quantity and there is an opposite order that satisfies price condition.
            while (incoming.getRemainingQuantity() > 0) {
                Order topOpp = oppQueue.peek();
                if (topOpp == null) break;

                boolean priceMatch;
                if (incoming.getOrderType() == OrderType.BUY) {
                    priceMatch = incoming.getPrice() >= topOpp.getPrice();
                } else {
                    priceMatch = incoming.getPrice() <= topOpp.getPrice();
                }

                if (!priceMatch) break;

                // remove opposite order to process it
                topOpp = oppQueue.poll();
                long matchQty = Math.min(incoming.getRemainingQuantity(), topOpp.getRemainingQuantity());
                double executionPrice = topOpp.getTimestamp().isBefore(incoming.getTimestamp()) ? topOpp.getPrice() : incoming.getPrice();
                // Simple executionPrice choice: use topOpp price if it was earlier, else incoming price.
                // Could be improved to mid-price or other rules.

                // Create trade
                Trade trade;
                if (incoming.getOrderType() == OrderType.BUY) {
                    trade = new Trade(IdGenerator.generateTradeId(), incoming.getOrderId(), topOpp.getOrderId(), executionPrice, matchQty, Instant.now(), incoming.getTradeType());
                } else {
                    trade = new Trade(IdGenerator.generateTradeId(), topOpp.getOrderId(), incoming.getOrderId(), executionPrice, matchQty, Instant.now(), incoming.getTradeType());
                }
                tradeRepository.saveTrade(trade);

                // Update quantities and statuses
                long newIncomingRemaining = incoming.getRemainingQuantity() - matchQty;
                incoming.setRemainingQuantity(newIncomingRemaining);

                long newOppRemaining = topOpp.getRemainingQuantity() - matchQty;
                topOpp.setRemainingQuantity(newOppRemaining);

                // If opposite still has remaining, put back into its queue (with same ordering semantics)
                if (topOpp.getRemainingQuantity() > 0) {
                    oppQueue.add(topOpp);
                } // else topOpp is filled

                // If incoming is filled break
                if (incoming.getRemainingQuantity() <= 0) {
                    break;
                }
                // else continue matching with next opposite
            }

            // If incoming still has quantity and was not filled, ensure it's in the queue (OrderService usually saves it)
            // The repository contains it because OrderService saved before calling match().

        } finally {
            lock.unlock();
        }
    }
}
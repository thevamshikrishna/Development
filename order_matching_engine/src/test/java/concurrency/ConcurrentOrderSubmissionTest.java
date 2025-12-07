package concurrency;

import model.*;
import org.junit.jupiter.api.Test;
import service.OrderService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;   // ← THIS LINE WAS MISSING!

class ConcurrentOrderSubmissionTest {
    @Test
    public void shouldHandle100ThreadsSubmitting10000OrdersWithoutRaceConditions() throws InterruptedException {
        OrderService orderService = new OrderService();

        ExecutorService pool = Executors.newFixedThreadPool(100);
        CountDownLatch latch = new CountDownLatch(10_000);

        for (int i = 0; i < 10_000; i++) {
            final int idx = i;
            pool.submit(() -> {
                try {
                    orderService.submitCsvOrder(
                            "CONC-" + idx,
                            "T" + (idx % 100),
                            TradeType.values()[idx % 3],
                            idx % 2 == 0 ? OrderType.BUY : OrderType.SELL,
                            100.0 + (idx % 100),
                            50,
                            "US",
                            Timestamp.from(Instant.now())
                    );
                } finally {
                    latch.countDown();
                }
            });
        }

        assertTrue(latch.await(30, TimeUnit.SECONDS));
        pool.shutdown();
        pool.awaitTermination(10, TimeUnit.SECONDS);

        long trades = orderService.getTradeRepository().getAllTrades().size();
        long filled = orderService.getOrderBook().getAllOrders()
                .stream()
                .filter(o -> o.getStatus() == OrderStatus.FILLED)
                .count();

        System.out.println("Concurrency test passed: " + trades + " trades, " + filled + " filled orders");

        // Proof that real matching happened under extreme concurrency
        assertTrue(trades > 800, "Expected many trades under concurrent load");
        assertTrue(filled > 1000, "Expected many orders to be fully filled");


        // 1. No negative quantity allowed
        boolean hasNegativeQty = orderService.getOrderBook().getAllOrders()
                .stream()
                .anyMatch(o -> o.getRemainingQuantity() < 0);

        assertFalse(hasNegativeQty, "No order should have negative remaining quantity!");

// 2. No duplicate trades
        long uniqueTrades = orderService.getTradeRepository().getAllTrades()
                .stream()
                .map(Trade::getTradeId)
                .distinct()
                .count();

        assertEquals(trades, uniqueTrades, "Trades must not be duplicated!");

// 3. Order book size consistent
        long totalOrders = orderService.getOrderBook().getAllOrders().size() + filled;

        assertEquals(10_000, totalOrders, "Total number of orders must match submitted orders!");

    }
}
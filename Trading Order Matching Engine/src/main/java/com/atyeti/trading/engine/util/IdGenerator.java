package com.atyeti.trading.engine.util;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
    private static final AtomicLong tradeCounter = new AtomicLong(1);

    public static String generateOrderId() {
        return "ORD-" + UUID.randomUUID().toString();
    }

    public static String generateTradeId() {
        return "TRD-" + tradeCounter.getAndIncrement();
    }
}


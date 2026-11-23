package com.atyeti.trading.engine.concurrency;

import com.atyeti.trading.engine.model.TradeType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class LockManager {
    private final Map<TradeType, ReentrantLock> locks = new ConcurrentHashMap<>();

    public LockManager() {
        for (TradeType t : TradeType.values()) {
            locks.put(t, new ReentrantLock());
        }
    }

    public ReentrantLock getLock(TradeType tradeType) {
        return locks.get(tradeType);
    }
}


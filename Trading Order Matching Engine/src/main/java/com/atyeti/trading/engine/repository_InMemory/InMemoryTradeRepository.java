package com.atyeti.trading.engine.repository_InMemory;

import com.atyeti.trading.engine.model.Trade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryTradeRepository implements TradeRepository {
    private final List<Trade> trades = new CopyOnWriteArrayList<>();

    @Override
    public void saveTrade(Trade trade) {
        trades.add(trade);
    }

    @Override
    public List<Trade> getAllTrades() {
        return Collections.unmodifiableList(new ArrayList<>(trades));
    }
}


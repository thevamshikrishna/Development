package com.atyeti.trading.engine.repository_InMemory;

import com.atyeti.trading.engine.model.Trade;

import java.util.List;

public interface TradeRepository {
    void saveTrade(Trade trade);
    List<Trade> getAllTrades();
}


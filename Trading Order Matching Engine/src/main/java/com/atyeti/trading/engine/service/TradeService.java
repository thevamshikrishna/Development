package com.atyeti.trading.engine.service;


import com.atyeti.trading.engine.model.Trade;
import com.atyeti.trading.engine.repository_InMemory.TradeRepository;

import java.util.List;

public class TradeService {
    private final TradeRepository tradeRepository;

    public TradeService(TradeRepository repo) {
        this.tradeRepository = repo;
    }

    public List<Trade> getAllTrades() {
        return tradeRepository.getAllTrades();
    }
}

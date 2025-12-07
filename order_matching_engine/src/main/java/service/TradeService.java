package service;

import inmemory.InMemoryTrade;

public class TradeService {
    private final InMemoryTrade inMemoryTrade;

    public TradeService(InMemoryTrade inMemoryTrade) {
        this.inMemoryTrade = inMemoryTrade;
    }
}

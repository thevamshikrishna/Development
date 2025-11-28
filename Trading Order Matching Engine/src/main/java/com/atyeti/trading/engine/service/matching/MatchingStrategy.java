package com.atyeti.trading.engine.service.matching;

import com.atyeti.trading.engine.model.Order;

public interface MatchingStrategy {
    void match(Order incoming);
}

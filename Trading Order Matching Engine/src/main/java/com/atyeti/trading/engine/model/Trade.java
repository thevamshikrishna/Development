package com.atyeti.trading.engine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;

@Data
@NoArgsConstructor(force=true)
@AllArgsConstructor
public class Trade {
    private final String tradeId;
    private final String buyOrderId;
    private final String sellOrderId;
    private final double price;
    private final long quantity;
    private final Instant timestamp;
    private final TradeType tradeType;
}

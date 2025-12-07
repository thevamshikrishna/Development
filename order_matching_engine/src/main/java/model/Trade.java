package model;

import lombok.*;

import java.sql.Timestamp;

@Getter
@ToString
@AllArgsConstructor
public class Trade {
    private String tradeId;
    private String buyOrderId;
    private String sellOrderId;
    private double price;
    private long quantity;
    private Timestamp timestamp;
    private TradeType tradeType;
}
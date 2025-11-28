package com.atyeti.trading.engine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String orderId;
    private String traderId;
    private TradeType tradeType;
    private OrderType orderType;
    private double price;
    private int quantity;
    private String countryCode;
    private Instant timestamp;

    private long remainingQuantity;
    private OrderStatus status;

    public Order(String orderId,
                 String traderId,
                 TradeType tradeType,
                 OrderType orderType,
                 double price,
                 long quantity,
                 String country,
                 Instant timestamp) {

        this.orderId = orderId;
        this.traderId = traderId;
        this.tradeType = tradeType;
        this.orderType = orderType;
        this.price = price;
        this.quantity = (int) quantity;
        this.remainingQuantity = quantity;
        this.countryCode = country;
        this.timestamp = timestamp;
        this.status = OrderStatus.PENDING;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }


    public void setRemainingQuantity(long remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
        if (this.remainingQuantity <= 0) {
            this.remainingQuantity = 0;
            this.status = OrderStatus.FILLED;
        } else if (this.remainingQuantity < this.quantity) {
            this.status = OrderStatus.PARTIALLY_FILLED;
        }
    }

    public void markRejected() {
        this.status = OrderStatus.REJECTED;
    }
}

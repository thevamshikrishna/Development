package com.atyeti.trading.engine.util;

import com.atyeti.trading.engine.model.Order;
import com.atyeti.trading.engine.model.Trade;

public class ConsoleLogger {

    public static void orderReceived(Order o) {
        System.out.println(
                "Order received: " + o.getOrderId() +
                        " | Trader " + o.getTraderId() +
                        " | " + o.getTradeType() + " " + o.getOrderType() +
                        " | Qty=" + o.getQuantity() +
                        " @ Price=" + o.getPrice()
        );
    }

    public static void orderRejected(String orderId, String reason) {
        System.out.println("REJECTED: " + orderId + " | Reason: " + reason);
    }

    public static void matchFound(Order buy, Order sell) {
        System.out.println("MATCH FOUND: BUY " + buy.getOrderId() +
                " ↔ SELL " + sell.getOrderId());
    }

    public static void tradeExecuted(Trade t) {
        System.out.println(
                "TRADE EXECUTED: TradeID=" + t.getTradeId() +
                        " | Qty=" + t.getQuantity() +
                        " @ Price=" + t.getPrice() +
                        " | Type=" + t.getTradeType()
        );
    }

    public static void orderStatusUpdated(Order o) {
        System.out.println("Order " + o.getOrderId() +
                " status → " + o.getStatus() +
                " | Remaining=" + o.getRemainingQuantity());
    }
}

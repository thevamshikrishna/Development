package model;

import java.sql.Timestamp;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {
        private String orderId;
        private String traderId;
        private TradeType tradeType;
        private OrderType orderType;
        private double price;
        private long quantity;
        private long remainingQuantity;
        private String country;
        private Timestamp timestamp;
        private OrderStatus status = OrderStatus.PENDING;

        public Order(String orderId, String traderId, TradeType tradeType, OrderType orderType,
                     double price, long quantity, String country, Timestamp timestamp) {
                this.orderId = orderId;
                this.traderId = traderId;
                this.tradeType = tradeType;
                this.orderType = orderType;
                this.price = price;
                this.quantity = quantity;
                this.remainingQuantity = quantity;
                this.country = country;
                this.timestamp = timestamp;
                this.status = OrderStatus.PENDING;
        }
}

//package model;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.sql.Timestamp;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Order {
//
//        private String orderId;
//        private String traderId;
//        private TradeType tradeType;
//        private OrderType orderType;
//        private double price;
//        private Long quantity;
//        private String countryCode;
//        private Timestamp timestamp;
//        private long remainingQuantity;
//}
package com.atyeti.trading.engine;

import com.atyeti.trading.engine.model.Order;
import com.atyeti.trading.engine.model.Trade;
import com.atyeti.trading.engine.model.TradeType;
import com.atyeti.trading.engine.service.OrderService;
import com.atyeti.trading.engine.service.TradeService;
import com.atyeti.trading.engine.util.CsvOrderReader;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        // STEP 1: Initialize OrderService (handles validation, storage & matching)
        OrderService orderService = new OrderService();

        // STEP 2: Provide paths for BUY and SELL order CSV files
        String buyCsv = "C:\\Users\\VamshiNallavalli\\OneDrive - Atyeti Inc\\Desktop\\My_Learnings\\Trading Order Matching Engine\\src\\main\\resources\\buy_orders.csv";
        String sellCsv = "C:\\Users\\VamshiNallavalli\\OneDrive - Atyeti Inc\\Desktop\\My_Learnings\\Trading Order Matching Engine\\src\\main\\resources\\sell_orders (2).csv";

        // STEP 3: Read and process BUY orders from CSV
        CsvOrderReader.processCsv(buyCsv, orderService);

        // STEP 4: Read and process SELL orders from CSV
        CsvOrderReader.processCsv(sellCsv, orderService);

        // STEP 5: Initialize TradeService using trade repository from OrderService
        TradeService tradeService = new TradeService(orderService.getTradeRepository());

        // STEP 6: Fetch all executed trades after order matching
        List<Trade> trades = tradeService.getAllTrades();

        // STEP 7: Fetch all orders (buy + sell) from the order book
        var allOrders = orderService.getOrderBookRepository().getAllOrders();

        // STEP 8: Count FILLED orders
        long filledOrders = allOrders.stream()
                .filter(o -> o.getStatus().toString().equals("FILLED"))
                .count();

        // STEP 9: Identify ACTIVE / UNMATCHED orders
        var activeOrders = allOrders.stream()
                .filter(o -> !o.getStatus().toString().equals("FILLED"))
                .toList();

        // STEP 10: Print global summary
        System.out.println("\n=== SUMMARY ===");
        System.out.println("Total Orders Loaded: " + orderService.getTotalOrdersLoaded());
        System.out.println("Orders Rejected: " + orderService.getRejectedOrders());
        System.out.println("Valid Orders Processed: " + orderService.getValidOrders());
        System.out.println("Total Trades Executed: " + trades.size());
        System.out.println("FILLED Orders: " + filledOrders);
        System.out.println("ACTIVE (Unmatched) Orders: " + activeOrders.size());

        // STEP 11: Print summary group-by TradeType (Equity, Forex, Commodity, etc.)
        System.out.println("\n=== MATCHING SUMMARY BY TRADE TYPE ===");

        for (TradeType type : TradeType.values()) {

            // STEP 11.1: Count executed trades for this TradeType
            long tradesByType = trades.stream()
                    .filter(t -> t.getTradeType() == type)
                    .count();

            // STEP 11.2: Count filled orders for this TradeType
            long filledByType = allOrders.stream()
                    .filter(o -> o.getTradeType() == type)
                    .filter(o -> o.getStatus().toString().equals("FILLED"))
                    .count();

            // STEP 11.3: Count unmatched orders for this TradeType
            long activeByType = allOrders.stream()
                    .filter(o -> o.getTradeType() == type)
                    .filter(o -> !o.getStatus().toString().equals("FILLED"))
                    .count();

            // STEP 11.4: Print the result for this type
            System.out.println(type + " → Trades Executed: " + tradesByType +
                    ", Filled Orders: " + filledByType +
                    ", Active Orders: " + activeByType);
        }

        // STEP 12: Print end message
        System.out.println("\n=== ENGINE RUN COMPLETE ===");
    }
}
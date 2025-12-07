import lombok.extern.slf4j.Slf4j;
import model.Order;
import model.OrderStatus;
import model.Trade;
import model.TradeType;
import service.OrderService;
import util.CsvReader;

import java.util.List;

@Slf4j
public class App {

    public static void main(String[] args) {
            log.info("Starting Trading Order Matching Engine...");
            log.info("Loading BUY orders from CSV...");

        // 1) Initialize OrderService (handles validation, storage & matching)
        OrderService orderService = new OrderService();

        // 2) Provide paths for BUY and SELL order CSV files
        String buyCsv = "src/main/resources/buy_orders.csv";
        String sellCsv = "src/main/resources/sell_orders.csv";

        // 3) Read and process BUY and SELL orders from CSV
        log.info("Loading BUY orders from: " + buyCsv);
        CsvReader.processCsv(buyCsv, orderService);

        log.info("Loading SELL orders from: " + sellCsv);
        CsvReader.processCsv(sellCsv, orderService);

        // 4) Retrieve all executed trades and current order book snapshot
        List<Trade> trades = orderService.getTradeRepository().getAllTrades();
        List<Order> allOrders = orderService.getOrderBook().getAllOrders();

        // 5) Calculate statistics by order status
        long filledOrders = allOrders.stream()
                .filter(o -> o.getStatus() == OrderStatus.FILLED)
                .count();

        long partiallyFilledOrders = allOrders.stream()
                .filter(o -> o.getStatus() == OrderStatus.PARTIALLY_FILLED)
                .count();

        long pendingOrders = allOrders.stream()
                .filter(o -> o.getStatus() == OrderStatus.PENDING)
                .count();

        // 6) Print global summary
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           TRADING ENGINE SUMMARY");
        System.out.println("=".repeat(50));
        System.out.println("Total Orders Loaded         : " + orderService.getTotalOrdersLoaded());
        System.out.println("Orders Rejected             : " + orderService.getRejectedOrders());
        System.out.println("Valid Orders Processed      : " + orderService.getValidOrders());
        System.out.println("Total Trades Executed       : " + trades.size());
        System.out.println();
        System.out.println("FILLED Orders               : " + filledOrders);
        System.out.println("PARTIALLY FILLED Orders     : " + partiallyFilledOrders);
        System.out.println("PENDING (Unmatched) Orders  : " + pendingOrders);
        System.out.println("=".repeat(50));

        // 7) Print matching summary grouped by TradeType
        System.out.println("\nMATCHING SUMMARY BY TRADE TYPE");
        System.out.println("-".repeat(60));
        for (TradeType type : TradeType.values()) {
            long tradesByType = trades.stream()
                    .filter(t -> t.getTradeType() == type)
                    .count();

            long filledByType = allOrders.stream()
                    .filter(o -> o.getTradeType() == type && o.getStatus() == OrderStatus.FILLED)
                    .count();

            long partialByType = allOrders.stream()
                    .filter(o -> o.getTradeType() == type && o.getStatus() == OrderStatus.PARTIALLY_FILLED)
                    .count();

            long pendingByType = allOrders.stream()
                    .filter(o -> o.getTradeType() == type && o.getStatus() == OrderStatus.PENDING)
                    .count();

            System.out.printf("%-8s → Trades: %5d | FILLED: %5d | PARTIAL: %5d | PENDING: %5d%n",
                    type, tradesByType, filledByType, partialByType, pendingByType);
        }

        log.info("Completed!");
        System.out.println("=".repeat(50));
    }
}
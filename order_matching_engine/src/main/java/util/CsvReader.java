package util;

import org.slf4j.*;
import model.OrderType;
import model.TradeType;
import service.OrderService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CsvReader {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final Logger log = LoggerFactory.getLogger(CsvReader.class);
    public static void processCsv(String filePath, OrderService orderService) {
        if (filePath == null || filePath.isBlank()) {
            System.err.println("CSV file path is null or empty.");
            return;
        }

        int lineNumber = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                lineNumber++;

                // Skip header and empty lines
                if (lineNumber == 1 || line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",", -1); // -1 keeps empty trailing fields

                if (parts.length < 8) {
                    log.warn("Line {}: Invalid format...", lineNumber);
                    log.warn("Line {}: Missing required field(s)", lineNumber);
                    continue;
                }

                String orderId = parts[0].trim();
                String traderId = parts[1].trim();
                String tradeTypeStr = parts[2].trim();
                String orderTypeStr = parts[3].trim();
                String priceStr = parts[4].trim();
                String quantityStr = parts[5].trim();
                String country = parts[6].trim();
                String timestampStr = parts[7].trim();

                // Basic required field check
                if (orderId.isBlank() || traderId.isBlank() || tradeTypeStr.isBlank() ||
                        orderTypeStr.isBlank() || priceStr.isBlank() || quantityStr.isBlank() ||
                        country.isBlank() || timestampStr.isBlank()) {
                    System.err.printf("Line %d: Missing required field(s): %s%n", lineNumber, line);
                    continue;
                }

                try {
                    TradeType tradeType = TradeType.valueOf(tradeTypeStr.toUpperCase());
                    OrderType orderType = OrderType.valueOf(orderTypeStr.toUpperCase());

                    double price = Double.parseDouble(priceStr);
                    long quantity = Long.parseLong(quantityStr);

                    LocalDateTime localDateTime;
                    try {
                        localDateTime = LocalDateTime.parse(timestampStr, FORMATTER);
                    } catch (DateTimeParseException e) {
                        System.err.printf("Line %d: Invalid timestamp format '%s' (expected yyyy-MM-dd HH:mm:ss)%n",
                                lineNumber, timestampStr);
                        continue;
                    }

                    Timestamp timestamp = Timestamp.valueOf(localDateTime);

                    // Submit to OrderService — it will validate and count rejects internally
                    orderService.submitCsvOrder(
                            orderId,
                            traderId,
                            tradeType,
                            orderType,
                            price,
                            quantity,
                            country.toUpperCase(), // standardize
                            timestamp
                    );

                } catch (IllegalArgumentException e) {
                    System.err.printf("Line %d: Invalid enum value: %s%n", lineNumber, e.getMessage());
                } catch (Exception e) {
                    System.err.printf("Line %d: Unexpected error processing order: %s%n", lineNumber, e.getMessage());
                }
            }

            System.out.println("Successfully processed CSV: " + filePath);

        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + filePath);
            e.printStackTrace();
        }
    }
}



//package util;
//
//import model.OrderType;
//import model.TradeType;
//import service.OrderService;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//public class CsvReader {
//
//    private static final DateTimeFormatter FORMATTER =
//            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//    public static void processCsv(String path, OrderService orderService) {
//
//        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
//
//            String line;
//            boolean header = true;
//
//            while ((line = br.readLine()) != null) {
//
//                if (header) {
//                    header = false;
//                    continue;
//                }
//
//                if (line.trim().isEmpty()) continue;
//
//                String[] parts = line.split(",");
//
//                if (parts.length != 8) {
//                    orderService.submitCsvOrder(
//                            "INVALID", "INVALID",
//                            TradeType.EQUITY, OrderType.BUY,
//                            0, 0, "NA",
//                            new Timestamp(System.currentTimeMillis())
//                    );
//                    continue;
//                }
//
//                try {
//                    // FIX #1: Convert CSV text to uppercase before enum lookup
//                    TradeType tradeType =
//                            TradeType.valueOf(parts[2].trim().toUpperCase());
//
//                    OrderType orderType =
//                            OrderType.valueOf(parts[3].trim().toUpperCase());
//
//                    double price = Double.parseDouble(parts[4].trim());
//                    long quantity = Long.parseLong(parts[5].trim());
//                    String country = parts[6].trim();
//
//                    Timestamp timestamp = Timestamp.valueOf(
//                            LocalDateTime.parse(parts[7].trim(), FORMATTER)
//                    );
//
//                    // Submit order to OrderService
//                    orderService.submitCsvOrder(
//                            parts[0].trim(),
//                            parts[1].trim(),
//                            tradeType,
//                            orderType,
//                            price,
//                            quantity,
//                            country,
//                            timestamp
//                    );
//
//                } catch (Exception e) {
//                    // DO NOT increment rejected here
//                    // OrderService already counts rejected orders
//                }
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
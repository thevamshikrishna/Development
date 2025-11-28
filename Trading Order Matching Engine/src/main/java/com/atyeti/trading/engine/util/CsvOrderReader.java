package com.atyeti.trading.engine.util;

import com.atyeti.trading.engine.model.Order;
import com.atyeti.trading.engine.model.OrderType;
import com.atyeti.trading.engine.model.TradeType;
import com.atyeti.trading.engine.service.OrderService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class CsvOrderReader {

    private static final DateTimeFormatter TIME_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void processCsv(String filePath, OrderService orderService) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            boolean header = true;

            while ((line = br.readLine()) != null) {

                if (header) {
                    header = false;
                    continue;
                }

                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");

                if (parts.length != 8) {
                    continue;
                }

                String orderId       = parts[0].trim();
                String traderId      = parts[1].trim();
                String tradeTypeText = parts[2].trim();
                String orderTypeText = parts[3].trim();
                String priceText     = parts[4].trim();
                String qtyText       = parts[5].trim();
                String country       = parts[6].trim();
                String timeText      = parts[7].trim();

                try {
                    TradeType tradeType = TradeType.valueOf(tradeTypeText);
                    OrderType orderType = OrderType.valueOf(orderTypeText);
                    double price        = Double.parseDouble(priceText);
                    long quantity       = Long.parseLong(qtyText);

                    LocalDateTime dateTime = LocalDateTime.parse(timeText, TIME_FORMAT);
                    Instant timestamp = dateTime.toInstant(ZoneOffset.UTC);

                    orderService.submitCsvOrder(
                            orderId, traderId, tradeType, orderType, price, quantity, country, timestamp
                    );

                } catch (Exception ignore) {
                    // ignore invalid row completely
                }
            }

        } catch (Exception ignore) {
            // ignore file load exceptions too
        }
    }
}
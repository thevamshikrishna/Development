package com.bank.util;

import com.bank.model.Transaction;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class ReportGenerator {

    private static final String LOG_FILE = "src/main/resources/transactions.log";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public synchronized void logTransaction(Transaction transaction) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(formatTransaction(transaction) + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }

    private String formatTransaction(Transaction t) {
        return String.format("[%s] TransactionID=%s | Type=%s | From=%s | To=%s | Amount=%.2f",
                t.getTimestamp().format(formatter),
                t.getTransactionId(),
                t.getType(),
                t.getFromAccountId(),
                t.getToAccountId(),
                t.getAmount());
    }
}
package com.atyeti;


import com.atyeti.pojo.Transaction;
import com.atyeti.service.TransactionReconciliationService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ReconciliationApp {
    public static void main(String[] args) {
        List<Transaction> bankTransactions= Arrays.asList(
                new Transaction("TXN001", 100.0, LocalDate.of(2025, 5, 1), "Salary"),
                new Transaction("TXN002", 200.0, LocalDate.of(2025, 5, 2), "Grocery"),
                new Transaction("TXN004", 400.0, LocalDate.of(2025, 5, 4), "Online Purchase")
        );


        List<Transaction> customerTransactions = Arrays.asList(
                new Transaction("TXN001", 100.0, LocalDate.of(2025, 5, 1), "Salary"),
                new Transaction("TXN002", 250.0, LocalDate.of(2025, 5, 2), "Grocery"),
                new Transaction("TXN003", 300.0, LocalDate.of(2025, 5, 3), "ATM Withdrawal")
        );

        TransactionReconciliationService service=new TransactionReconciliationService();
        Map<String,List<Transaction>> mismatches=service.reconcile(bankTransactions,customerTransactions);


        mismatches.forEach((reason, txns) -> {
            System.out.println("\n" + reason + ":");
            txns.forEach(System.out::println);
        });


    }
}
package com.bank;

import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.model.Transaction.Type;
import com.bank.service.TransactionProcessor;
import com.bank.service.TransactionQueue;
import com.bank.util.ReportGenerator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Create accounts
        Account account1 = new Account("Alice", new BigDecimal("1000.00"));
        Account account2 = new Account("Bob", new BigDecimal("500.00"));

        Map<Long, Account> accounts = new HashMap<>();
        accounts.put(account1.getAccountId(), account1);
        accounts.put(account2.getAccountId(), account2);

        // Initialize components
        TransactionQueue queue = new TransactionQueue();
        ReportGenerator reportGenerator = new ReportGenerator();
        TransactionProcessor processor = new TransactionProcessor(queue, accounts, reportGenerator);

        // Start processor thread
        Thread processorThread = new Thread(processor);
        processorThread.start();

        // Create and add transactions
        queue.addTransaction(new Transaction(null, account1.getAccountId(), new BigDecimal("200.00"), Type.DEPOSIT));
        queue.addTransaction(new Transaction(account1.getAccountId(), null, new BigDecimal("100.00"), Type.WITHDRAWAL));
        queue.addTransaction(new Transaction(account1.getAccountId(), account2.getAccountId(), new BigDecimal("150.00"), Type.TRANSFER));

        // Give some time to process
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {}

        // Stop processor thread safely
        processorThread.interrupt();

        // Print final balances
        System.out.println("Final Balances:");
        System.out.println(account1.getAccountHolder() + " => " + account1.getBalance());
        System.out.println(account2.getAccountHolder() + " => " + account2.getBalance());
    }
}
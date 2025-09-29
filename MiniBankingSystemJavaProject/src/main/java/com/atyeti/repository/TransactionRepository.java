package com.atyeti.repository;

import java.util.*;

public class TransactionRepository implements ITransactionRepository {
    private final Map<Long, Double> balances = new HashMap<>();
    private final Map<Long, List<String>> transactions = new HashMap<>();

    public void addTransaction(long accNumber, String txn) {
        transactions.computeIfAbsent(accNumber, k -> new ArrayList<>()).add(txn);
    }

    public List<String> getTransactions(long accNumber) {
        return transactions.getOrDefault(accNumber, new ArrayList<>());
    }

    public double getBalance(long accNumber) {
        return balances.getOrDefault(accNumber, 0.0);
    }

    public void setBalance(long accNumber, double amount) {
        balances.put(accNumber, amount);
    }
}
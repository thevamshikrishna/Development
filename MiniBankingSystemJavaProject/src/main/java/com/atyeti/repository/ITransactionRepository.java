package com.atyeti.repository;

import java.util.List;

public interface ITransactionRepository {
    void addTransaction(long accNumber, String txn);
    List<String> getTransactions(long accNumber);
    double getBalance(long accNumber);
    void setBalance(long accNumber, double amount);
}
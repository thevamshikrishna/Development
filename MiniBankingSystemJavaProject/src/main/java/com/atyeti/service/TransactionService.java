package com.atyeti.service;

import com.atyeti.model.Account;
import com.atyeti.repository.ITransactionRepository;

import java.time.LocalDateTime;

public class TransactionService implements ITransactionService {
    private final ITransactionRepository repo;

    public TransactionService(ITransactionRepository repo) {
        this.repo = repo;
    }

    public void deposit(Account acc, double amount) {
        double bal = repo.getBalance(acc.getAccountNumber());
        repo.setBalance(acc.getAccountNumber(), bal + amount);
        repo.addTransaction(acc.getAccountNumber(), "Deposited ₹" + amount + " at " + LocalDateTime.now());
        System.out.println("₹" + amount + " deposited.");
    }

    public void withdraw(Account acc, double amount) {
        double bal = repo.getBalance(acc.getAccountNumber());
        if (bal >= amount) {
            repo.setBalance(acc.getAccountNumber(), bal - amount);
            repo.addTransaction(acc.getAccountNumber(), "Withdrew ₹" + amount + " at " + LocalDateTime.now());
            System.out.println("₹" + amount + " withdrawn.");
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void checkBalance(Account acc) {
        System.out.println("Balance: ₹" + repo.getBalance(acc.getAccountNumber()));
    }

    public void showTransactions(Account acc) {
        System.out.println("Transactions:");
        repo.getTransactions(acc.getAccountNumber()).forEach(System.out::println);
    }
}
package com.atyeti.service;

import com.atyeti.model.Account;

public interface ITransactionService {
    void deposit(Account acc, double amount);
    void withdraw(Account acc, double amount);
    void checkBalance(Account acc);
    void showTransactions(Account acc);
}

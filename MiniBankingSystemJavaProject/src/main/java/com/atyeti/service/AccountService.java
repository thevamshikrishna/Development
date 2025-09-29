package com.atyeti.service;

import com.atyeti.model.Account;
import com.atyeti.repository.IAccountRepository;

import java.util.Optional;
import java.util.Random;

public class AccountService implements IAccountService {
    private final IAccountRepository repo;
    private final Random rand = new Random();

    public AccountService(IAccountRepository repo) {
        this.repo = repo;
    }

    public Account createAccount(String name, long mobileNo, String password) {
        long accNum = 1000000000L + rand.nextInt(9999999);
        Account acc = new Account(name, accNum, mobileNo, password);
        repo.save(acc);
        System.out.println("Account Created with Account Number: "+accNum);
        return acc;
    }

    public Optional<Account> login(String name, String password) {
        return repo.getAllAccounts().stream()
                .filter(acc -> acc.getUserName().equals(name) && acc.getPassword().equals(password))
                .findFirst();
    }
}
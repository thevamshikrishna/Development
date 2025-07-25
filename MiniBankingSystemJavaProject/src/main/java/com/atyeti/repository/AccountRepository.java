package com.atyeti.repository;

import com.atyeti.model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountRepository implements IAccountRepository {
    private final List<Account> accounts = new ArrayList<>();

    public void save(Account account) {
        accounts.add(account);
    }

    public List<Account> getAllAccounts() {
        return accounts;
    }
}
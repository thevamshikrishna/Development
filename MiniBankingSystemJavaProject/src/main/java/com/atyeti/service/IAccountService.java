package com.atyeti.service;


import com.atyeti.model.Account;
import java.util.Optional;

public interface IAccountService {
    Account createAccount(String name, long mobileNo, String password);
    Optional<Account> login(String name, String password);
}

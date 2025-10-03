package com.atyeti.repository;

import com.atyeti.model.Account;
import java.util.List;

public interface IAccountRepository {
    void save(Account account);
    List<Account> getAllAccounts();
}

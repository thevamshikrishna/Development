package com.bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private static final AtomicLong ID_GENERATOR=new AtomicLong(1000);

    private Long accountId;
    private String accountHolder;
    private BigDecimal balance;

    public Account(String accountHolder, BigDecimal balance) {
        this.accountId = ID_GENERATOR.getAndIncrement();
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public synchronized void deposit(BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO)<=0){
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance=balance.add(amount);
    }

    public synchronized void withdraw(BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO)<=0){
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if(balance.compareTo(amount)<0){
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance=balance.subtract(amount);
    }
}

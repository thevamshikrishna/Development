package com.atyeti.model;

public class Account {
    private String userName;
    private long accountNumber;
    private long mobileNumber;
    private String password;

    public Account(String userName, long accountNumber, long mobileNumber, String password) {
        this.userName = userName;
        this.accountNumber = accountNumber;
        this.mobileNumber = mobileNumber;
        this.password = password;
    }

    public String getUserName() { return userName; }
    public long getAccountNumber() { return accountNumber; }
    public long getMobileNumber() { return mobileNumber; }
    public String getPassword() { return password; }
}
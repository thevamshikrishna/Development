package bankaccountapp.pojo;

import bankaccountapp.service.Account;

public class Bank {
    private String name;
    private long securityNumber;
    private Account accountType;
    private double deposit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSecurityNumber() {
        return securityNumber;
    }

    public void setSecurityNumber(long securityNumber) {
        this.securityNumber = securityNumber;
    }

    public Account getAccountType() {
        return accountType;
    }

    public void setAccountType(Account accountType) {
        this.accountType = accountType;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public void setAccountType(String s) {
    }

    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                ", securityNumber=" + securityNumber +
                ", accountType=" + accountType +
                ", deposit=" + deposit +
                '}'+"\n";
    }
}

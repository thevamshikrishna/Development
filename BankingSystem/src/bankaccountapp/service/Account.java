package bankaccountapp.service;

import bankaccountapp.pojo.Bank;

public abstract class Account implements IBaseRate {

    public static void deposit(double amount) {
        Bank obj=new Bank();
        obj.setDeposit(amount);
    }

    public static void withdraw(){

    }

    public static void transfer(){

    }

    public static  void showInfo(){

    }

}

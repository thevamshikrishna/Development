//package com.bank.service;
//
//import com.bank.model.Account;
//import com.bank.model.Transaction;
//import com.bank.util.ReportGenerator;
//
//import java.util.Map;
//
//public class TransactionProcessor {
//
//    private final TransactionQueue queue;
//    private final Map<Long, Account> accounts;
//    private final ReportGenerator reportGenerator;
//
//    public TransactionProcessor(TransactionQueue queue, Map<Long, Account> accounts, ReportGenerator reportGenerator) {
//        this.queue = queue;
//        this.accounts = accounts;
//        this.reportGenerator = reportGenerator;
//    }
//
//    public void run(){
//        while(true){
//            try{
//                Transaction transaction=queue.getNextTransaction();
//                processTransaction(transaction);
//            }catch (InterruptedException e){
//                Thread.currentThread().interrupt();
//                break;
//            }
//            catch (Exception e){
//                System.err.println("Error processing transaction: "+e.getMessage());
//            }
//        }
//    }
//
//    private void processTransaction(Transaction t){
//        switch (t.getType()){
//            case DEPOSIT->handleDeposit(t);
//            case WITHDRAWAL -> handleWithdrawal(t);
//            case TRANSFER -> handleTransfer(t);
//        }
//        reportGenerator.logTransaction(t);
//    }
//
//    private void handleDeposit(Transaction t){
//        Account account=accounts.get(t.getToAccountId());
//        if(account!=null){
//            account.deposit(t.getAmount());
//        }else{
//            throw new IllegalArgumentException("Account not found for deposit: "+t.getTransactionId());
//        }
//    }
//
//    private void handleWithdrawal(Transaction t){
//        Account account=accounts.get(t.getFromAccountId());
//
//        if(account!=null){
//            account.withdraw(t.getAmount());
//        }else{
//            throw new IllegalArgumentException("Account not found for withdrawal: "+t.getFromAccountId());
//        }
//    }
//
//    private void handleTransfer(Transaction t){
//        Account from=accounts.get(t.getFromAccountId());
//        Account to=accounts.get(t.getToAccountId());
//
//        if(from == null || to == null){
//            throw new IllegalArgumentException("Invalid accounts for transfer");
//        }
//
//        from.withdraw(t.getAmount());
//        to.deposit(t.getAmount());
//    }
//}
package com.bank.service;

import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.model.Transaction.Type;
import com.bank.util.ReportGenerator;

import java.util.Map;

public class TransactionProcessor implements Runnable {
    // ✅ implements Runnable is required

    private final TransactionQueue queue;
    private final Map<Long, Account> accounts;
    private final ReportGenerator reportGenerator;

    public TransactionProcessor(TransactionQueue queue, Map<Long, Account> accounts, ReportGenerator reportGenerator) {
        this.queue = queue;
        this.accounts = accounts;
        this.reportGenerator = reportGenerator;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Transaction transaction = queue.getNextTransaction();
                processTransaction(transaction);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break; // stop gracefully
            } catch (Exception e) {
                System.err.println("Error processing transaction: " + e.getMessage());
            }
        }
    }

    private void processTransaction(Transaction t) {
        switch (t.getType()) {
            case DEPOSIT -> handleDeposit(t);
            case WITHDRAWAL -> handleWithdrawal(t);
            case TRANSFER -> handleTransfer(t);
        }

        reportGenerator.logTransaction(t);
    }

    private void handleDeposit(Transaction t) {
        Account account = accounts.get(t.getToAccountId());
        if (account != null) {
            account.deposit(t.getAmount());
        } else {
            throw new IllegalArgumentException("Account not found for deposit: " + t.getToAccountId());
        }
    }

    private void handleWithdrawal(Transaction t) {
        Account account = accounts.get(t.getFromAccountId());
        if (account != null) {
            account.withdraw(t.getAmount());
        } else {
            throw new IllegalArgumentException("Account not found for withdrawal: " + t.getFromAccountId());
        }
    }

    private void handleTransfer(Transaction t) {
        Account from = accounts.get(t.getFromAccountId());
        Account to = accounts.get(t.getToAccountId());

        if (from == null || to == null) {
            throw new IllegalArgumentException("Invalid accounts for transfer");
        }

        from.withdraw(t.getAmount());
        to.deposit(t.getAmount());
    }
}
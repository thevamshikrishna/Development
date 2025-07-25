package com.atyeti;

import com.atyeti.model.Account;
import com.atyeti.repository.AccountRepository;
import com.atyeti.repository.TransactionRepository;
import com.atyeti.service.AccountService;
import com.atyeti.service.TransactionService;

import java.util.Optional;
import java.util.Scanner;

public class BankingApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AccountRepository accRepo = new AccountRepository();
        TransactionRepository txnRepo = new TransactionRepository();

        AccountService accService = new AccountService(accRepo);
        TransactionService txnService = new TransactionService(txnRepo);

        while (true) {
            System.out.println("\n1. Create Account 2. Login 3. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Mobile: ");
                    long mob = sc.nextLong();
                    sc.nextLine();
                    System.out.print("Password: ");
                    String pwd = sc.nextLine();
                    Account acc = accService.createAccount(name, mob, pwd);
                    System.out.println("Account created. Number: " + acc.getAccountNumber());
                    break;

                case 2:
                    System.out.print("Username: ");
                    String uname = sc.nextLine();
                    System.out.print("Password: ");
                    String upwd = sc.nextLine();
                    Optional<Account> opt = accService.login(uname, upwd);
                    if (opt.isPresent()) {
                        Account user = opt.get();
                        System.out.println("Login successful.");
                        while (true) {
                            System.out.println("\n1.Deposit 2.Withdraw 3.Balance 4.Transactions 5.Logout");
                            int op = sc.nextInt();

                            switch (op) {
                                case 1:
                                    System.out.print("Amount: ");
                                    txnService.deposit(user, sc.nextDouble());
                                    break;

                                case 2:
                                    System.out.print("Amount: ");
                                    txnService.withdraw(user, sc.nextDouble());
                                    break;

                                case 3:
                                    txnService.checkBalance(user);
                                    break;

                                case 4:
                                    txnService.showTransactions(user);
                                    break;

                                case 5:
                                    System.out.println("Logged out.");
                                    break;

                                default:
                                    System.out.println("Invalid.");
                            }

                            if (op == 5) break;
                        }
                    } else {
                        System.out.println("Invalid credentials.");
                    }
                    break;

                case 3:
                    System.out.println("Thank you!");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

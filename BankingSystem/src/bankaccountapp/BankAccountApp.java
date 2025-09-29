package bankaccountapp;

import bankaccountapp.pojo.Bank;
import bankaccountapp.service.ReaderService;

import java.io.IOException;
import java.util.List;

public class BankAccountApp {
    public static final String PATH="C:\\Users\\VamshiNallavalli\\OneDrive - Atyeti Inc\\Desktop\\Atyeti_Vamshi_Java\\Core Java\\Learning Project Building (14,15,16,19,20-05-2025)\\POC's\\Oops\\BankingSystem\\src\\bankaccountapp\\data\\NewBankAccounts.csv";
    public static void main(String[] args) throws IOException {
        // Read a .csv file of names, social security numbers, account type, and initial deposit
        // Use a proper ds to hold all these accounts
       List<Bank> customerDetails= ReaderService.readCSV();

//        System.out.println(customerDetails);

    }
}
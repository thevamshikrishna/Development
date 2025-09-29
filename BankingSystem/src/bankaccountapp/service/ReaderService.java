package bankaccountapp.service;

import bankaccountapp.pojo.Bank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static bankaccountapp.BankAccountApp.PATH;

public class ReaderService {
    public static List<Bank> readCSV(){
        List<Bank> list=new ArrayList<>();

        try(BufferedReader reader=new BufferedReader(new FileReader(PATH))){
            String line=" ";

            while((line=reader.readLine())!=null){
                String[] details=line.split(",");

                Bank bank=new Bank();

                bank.setName(details[0]);
                bank.setSecurityNumber(Long.parseLong(details[1]));
                bank.setAccountType(details[2]);
                bank.setDeposit(Double.parseDouble(details[3]));

                list.add(bank);

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}

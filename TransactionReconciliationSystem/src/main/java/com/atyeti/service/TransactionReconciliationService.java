package com.atyeti.service;

import com.atyeti.pojo.Transaction;
import com.atyeti.util.TransactionMismatchReason;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransactionReconciliationService {
    public Map<String, List<Transaction>> reconcile(List<Transaction> bankTransactions, List<Transaction> customerTransactions) {
        Map<String,List<Transaction>> mismatches=new HashMap<>();

        Map<String,Transaction> bankMap=bankTransactions.stream()
                .collect(Collectors.toMap(Transaction::getTransactionId, t->t));

        Map<String,Transaction> customerMap= customerTransactions.stream()
                .collect(Collectors.toMap(Transaction::getTransactionId, Function.identity()));


        List<Transaction> amountMismatch=new ArrayList<>();
        List<Transaction> missingInBank=new ArrayList<>();
        List<Transaction> missingInCustomer=new ArrayList<>();


        for(Transaction bankTxn:bankTransactions){
            if(!customerMap.containsKey(bankTxn.getTransactionId()))
                missingInCustomer.add(bankTxn);
        }

        for(Transaction cusTxn:customerTransactions){
            Transaction bankTxn = bankMap.get(cusTxn.getTransactionId());
            if (bankTxn == null) {
                missingInBank.add(cusTxn);
            } else if (Double.compare(cusTxn.getAmount(), bankTxn.getAmount()) != 0) {
                amountMismatch.add(cusTxn);
            }
        }

        if (!amountMismatch.isEmpty())
            mismatches.put(TransactionMismatchReason.AMOUNT_MISMATCH,amountMismatch);

        if (!missingInBank.isEmpty())
            mismatches.put(TransactionMismatchReason.MISSING_IN_BANK,missingInBank);

        if (!missingInCustomer.isEmpty())
            mismatches.put(TransactionMismatchReason.MISSING_IN_CUSTOMER,missingInCustomer);

        return mismatches;

    }
}

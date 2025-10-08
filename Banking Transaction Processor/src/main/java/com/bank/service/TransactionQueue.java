package com.bank.service;

import com.bank.model.Transaction;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TransactionQueue {

    private final BlockingQueue<Transaction> queue=new LinkedBlockingQueue<>();

    public void addTransaction(Transaction transaction){
        queue.offer(transaction);
    }

    public Transaction getNextTransaction() throws InterruptedException{
        return queue.take();
    }

    public boolean isEmpty(){
        return queue.isEmpty();
    }
}

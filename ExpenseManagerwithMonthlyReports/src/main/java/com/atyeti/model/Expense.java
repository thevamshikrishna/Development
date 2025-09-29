package com.atyeti.model;

import java.time.LocalDate;

public class Expense {
    private int amount;
    private String description;
    private LocalDate date;
    private String category;

//    public Expense(int amount, String description, Date date, String category) {
//        this.amount = amount;
//        this.description = description;
//        this.date = date;
//        this.category = category;
//    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", category='" + category + '\'' +
                '}';
    }
}

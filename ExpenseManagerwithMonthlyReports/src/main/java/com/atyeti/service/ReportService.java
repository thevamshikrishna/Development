package com.atyeti.service;

import com.atyeti.model.Expense;

import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

public class ReportService implements IReportService{

    public void generateMonthlyReport(List<Expense> expenses, YearMonth month) {
        List<Expense> monthlyExpenses = expenses.stream()
                .filter(e -> YearMonth.from(e.getDate()).equals(month))
                .collect(Collectors.toList());

        if (monthlyExpenses.isEmpty()) {
            System.out.println("No expenses found for " + month);
            return;
        }

        double total = monthlyExpenses.stream().mapToDouble(Expense::getAmount).sum();
        double avgPerDay = total / month.lengthOfMonth();

        Map<String, Double> byCategory = monthlyExpenses.stream()
                .collect(Collectors.groupingBy(
                        e -> e.getCategory().isEmpty() ? "Uncategorized" : e.getCategory(),
                        Collectors.summingDouble(Expense::getAmount)
                ));

        Optional<Expense> maxExpense = monthlyExpenses.stream()
                .max(Comparator.comparingDouble(Expense::getAmount));

        Optional<Expense> minExpense = monthlyExpenses.stream()
                .min(Comparator.comparingDouble(Expense::getAmount));

        System.out.println("\n=== Monthly Expense Report: " + month + " ===");
        System.out.printf("Total Spent: ₹%.2f\n", total);
        System.out.printf("Average Daily Expense: ₹%.2f\n", avgPerDay);
        System.out.println("Most Expensive Entry: " + maxExpense.orElse(null));
        System.out.println("Least Expensive Entry: " + minExpense.orElse(null));
        System.out.println("\nBy Category:");
        byCategory.forEach((cat, amt) -> System.out.printf(" - %-12s : ₹%.2f\n", cat, amt));
              //  .forEach(entry -> System.out.println("Final: " + entry.getKey() + " ₹" + entry.getValue()));
    }

}

package com.atyeti.service;

import com.atyeti.exception.NoFileFoundException;
import com.atyeti.model.Expense;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.*;

import static com.atyeti.util.ExpenseUtils.FILE_PATH;

public class ExpenseService implements IExpenseService{

    public List<Expense> readDirectory() {
        File folder = new File(FILE_PATH);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".csv"));

        if (files == null || files.length == 0) {
            throw new NoFileFoundException("No CSV files found in directory.");
        }

        ExecutorService executor = Executors.newFixedThreadPool(files.length);
        List<Future<List<Expense>>> futures = new ArrayList<>();

        for (File file : files) {
            futures.add(executor.submit(() -> readFile(file.getAbsolutePath())));
        }

        List<Expense> allExpenses = new ArrayList<>();
        for (Future<List<Expense>> future : futures) {
            try {
                allExpenses.addAll(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
        return allExpenses;
    }

    public List<Expense> readFile(String path) {
        List<Expense> listOfExpense = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine && line.startsWith("Amount")) {
                    isFirstLine = false;
                    continue;
                }

                String[] data = line.split(",", -1);
                if (data.length < 4) continue;

                try {
                    Expense expense = new Expense();
                    expense.setAmount((int) Double.parseDouble(data[0].trim()));
                    expense.setDescription(data[1].trim());
                    expense.setDate(LocalDate.parse(data[2].trim()));
                    expense.setCategory(data[3].trim());

                    listOfExpense.add(expense);
                } catch (Exception ex) {
                    System.err.println("Error parsing line: " + line + " → " + ex.getMessage());
                }
            }

        } catch (IOException e) {
            throw new NoFileFoundException("Error reading file: " + path);
        }

        return listOfExpense;
    }
}
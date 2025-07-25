package com.atyeti.service;

import com.atyeti.model.Expense;

import java.util.List;

public interface IExpenseService {
    List<Expense> readDirectory();
    List<Expense> readFile(String path);

}

package com.atyeti.service;

import com.atyeti.model.Expense;

import java.time.YearMonth;
import java.util.List;

public interface IReportService {
    void generateMonthlyReport(List<Expense> expenses, YearMonth month);
}

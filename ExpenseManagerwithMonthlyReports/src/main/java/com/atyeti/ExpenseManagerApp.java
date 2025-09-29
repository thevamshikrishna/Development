package com.atyeti;

import com.atyeti.model.Expense;
import com.atyeti.service.IExpenseService;
import com.atyeti.service.IReportService;
import com.atyeti.service.ExpenseService;
import com.atyeti.service.ReportService;

import java.time.YearMonth;
import java.util.List;

public class ExpenseManagerApp {

    private final IExpenseService expenseService;
    private final IReportService reportService;

    public ExpenseManagerApp(IExpenseService expenseService, IReportService reportService) {
        this.expenseService = expenseService;
        this.reportService = reportService;
    }

    public void run() {
        try {
            List<Expense> allExpenses = expenseService.readDirectory();
            YearMonth selectedMonth = YearMonth.of(2025, 7);
            reportService.generateMonthlyReport(allExpenses, selectedMonth);
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        IExpenseService expenseService = new ExpenseService();
        IReportService reportService = new ReportService();
        ExpenseManagerApp app = new ExpenseManagerApp(expenseService, reportService);
        app.run();
    }
}

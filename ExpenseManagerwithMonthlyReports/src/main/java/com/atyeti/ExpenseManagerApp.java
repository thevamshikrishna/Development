package com.atyeti;

import com.atyeti.model.Expense;
import com.atyeti.service.IExpenseService;
import com.atyeti.service.IReportService;
import com.atyeti.service.ExpenseService;
import com.atyeti.service.ReportService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.YearMonth;
import java.util.List;
import java.util.Properties;

public class ExpenseManagerApp {

    private final IExpenseService expenseService;
    private final IReportService reportService;
    private static Properties prop=new Properties();

    public ExpenseManagerApp(IExpenseService expenseService, IReportService reportService) {
        this.expenseService = expenseService;
        this.reportService = reportService;
    }

    public void run() {
        try {
            List<Expense> allExpenses = expenseService.readDirectory(prop.getProperty("input.path"));
            YearMonth selectedMonth = YearMonth.of(2025, 7);
            reportService.generateMonthlyReport(allExpenses, selectedMonth);
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        prop = readProperties(args[0]);

        IExpenseService expenseService = new ExpenseService();
        IReportService reportService = new ReportService();

        ExpenseManagerApp app = new ExpenseManagerApp(expenseService, reportService);
        app.run();
    }

    private static Properties readProperties(String arg) throws FileNotFoundException {
        Properties props = new Properties();
        try (FileInputStream fils = new FileInputStream(arg)
        ) {
            props.load(fils);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return props;
    }
}

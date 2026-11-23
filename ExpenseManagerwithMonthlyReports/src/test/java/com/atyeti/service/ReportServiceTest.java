
package com.atyeti.service;

import com.atyeti.model.Expense;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReportServiceTest {

    private ReportService reportService;

    private final ByteArrayOutputStream output =
            new ByteArrayOutputStream();

    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        reportService = new ReportService();

        originalOut = System.out;

        System.setOut(new PrintStream(output));
    }

    // Restore console output after every test
    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    // Helper method to create Expense objects
    private Expense createExpense(
            int amount,
            String description,
            String date,
            String category) {

        Expense expense = new Expense();

        expense.setAmount(amount);
        expense.setDescription(description);
        expense.setDate(LocalDate.parse(date));
        expense.setCategory(category);

        return expense;
    }

    // Test 1: Generate report with valid expenses
    @Test
    void testGenerateMonthlyReportWithValidExpenses() {

        List<Expense> expenses = new ArrayList<>();

        expenses.add(createExpense(
                500, "Lunch", "2026-11-05", "Food"));

        expenses.add(createExpense(
                1000, "Shopping", "2026-11-10", "Shopping"));

        expenses.add(createExpense(
                300, "Dinner", "2026-11-15", "Food"));

        reportService.generateMonthlyReport(
                expenses, YearMonth.of(2026, 11));

        String result = output.toString();

        assertTrue(result.contains("2026-11"));
        assertTrue(result.contains("Total Spent: ₹1800.00"));
        assertTrue(result.contains("Average Daily Expense: ₹60.00"));
        assertTrue(result.contains("Food"));
        assertTrue(result.contains("Shopping"));
    }

    // Test 2: No expenses available
    @Test
    void testGenerateMonthlyReportWithEmptyList() {

        List<Expense> expenses = new ArrayList<>();

        reportService.generateMonthlyReport(
                expenses, YearMonth.of(2026, 11));

        String result = output.toString();

        assertTrue(result.contains(
                "No expenses found for 2026-11"));
    }

    // Test 3: Expenses belong to another month
    @Test
    void testGenerateMonthlyReportWithDifferentMonth() {

        List<Expense> expenses = new ArrayList<>();

        expenses.add(createExpense(
                500, "Lunch", "2026-10-05", "Food"));

        reportService.generateMonthlyReport(
                expenses, YearMonth.of(2026, 11));

        String result = output.toString();

        assertTrue(result.contains(
                "No expenses found for 2026-11"));
    }

    // Test 4: Verify total expense calculation
    @Test
    void testTotalExpenseCalculation() {

        List<Expense> expenses = new ArrayList<>();

        expenses.add(createExpense(
                100, "Tea", "2026-11-01", "Food"));

        expenses.add(createExpense(
                200, "Lunch", "2026-11-02", "Food"));

        expenses.add(createExpense(
                300, "Travel", "2026-11-03", "Transport"));

        reportService.generateMonthlyReport(
                expenses, YearMonth.of(2026, 11));

        assertTrue(output.toString().contains(
                "Total Spent: ₹600.00"));
    }

    // Test 5: Verify average daily expense
    @Test
    void testAverageDailyExpenseCalculation() {

        List<Expense> expenses = new ArrayList<>();

        expenses.add(createExpense(
                900, "Shopping", "2026-11-10", "Shopping"));

        reportService.generateMonthlyReport(
                expenses, YearMonth.of(2026, 11));

        // November has 30 days.
        assertTrue(output.toString().contains(
                "Average Daily Expense: ₹30.00"));
    }

    // Test 6: Verify category-wise expenses
    @Test
    void testCategoryWiseExpenseCalculation() {

        List<Expense> expenses = new ArrayList<>();

        expenses.add(createExpense(
                500, "Lunch", "2026-11-05", "Food"));

        expenses.add(createExpense(
                300, "Dinner", "2026-11-06", "Food"));

        expenses.add(createExpense(
                1000, "Shopping", "2026-11-07", "Shopping"));

        reportService.generateMonthlyReport(
                expenses, YearMonth.of(2026, 11));

        String result = output.toString();

        assertTrue(result.contains("Food"));
        assertTrue(result.contains("₹800.00"));
        assertTrue(result.contains("Shopping"));
        assertTrue(result.contains("₹1000.00"));
    }

    // Test 7: Verify Uncategorized expenses
    @Test
    void testUncategorizedExpenses() {

        List<Expense> expenses = new ArrayList<>();

        expenses.add(createExpense(
                400, "Miscellaneous", "2026-11-05", ""));

        reportService.generateMonthlyReport(
                expenses, YearMonth.of(2026, 11));

        String result = output.toString();

        assertTrue(result.contains("Uncategorized"));
        assertTrue(result.contains("₹400.00"));
    }

    // Test 8: Verify most expensive expense
    @Test
    void testMostExpensiveEntry() {

        List<Expense> expenses = new ArrayList<>();

        expenses.add(createExpense(
                200, "Tea", "2026-11-01", "Food"));

        expenses.add(createExpense(
                2000, "Shopping", "2026-11-02", "Shopping"));

        expenses.add(createExpense(
                500, "Travel", "2026-11-03", "Transport"));

        reportService.generateMonthlyReport(
                expenses, YearMonth.of(2026, 11));

        assertTrue(output.toString().contains("Shopping"));
    }

    // Test 9: Verify least expensive expense
    @Test
    void testLeastExpensiveEntry() {

        List<Expense> expenses = new ArrayList<>();

        expenses.add(createExpense(
                200, "Tea", "2026-11-01", "Food"));

        expenses.add(createExpense(
                2000, "Shopping", "2026-11-02", "Shopping"));

        expenses.add(createExpense(
                500, "Travel", "2026-11-03", "Transport"));

        reportService.generateMonthlyReport(
                expenses, YearMonth.of(2026, 11));

        assertTrue(output.toString().contains("Tea"));
    }

    // Test 10: Leap year February report
    @Test
    void testLeapYearFebruaryAverageExpense() {

        List<Expense> expenses = new ArrayList<>();

        expenses.add(createExpense(
                2800, "Shopping", "2024-02-15", "Shopping"));

        reportService.generateMonthlyReport(
                expenses, YearMonth.of(2024, 2));

        // February 2024 has 29 days.
        assertTrue(output.toString().contains(
                "Average Daily Expense: ₹96.55"));
    }

    // Test 11: Expenses from multiple months
    @Test
    void testMultipleMonthsExpenses() {

        List<Expense> expenses = new ArrayList<>();

        expenses.add(createExpense(
                500, "Lunch", "2026-10-05", "Food"));

        expenses.add(createExpense(
                1000, "Shopping", "2026-11-10", "Shopping"));

        expenses.add(createExpense(
                300, "Dinner", "2026-12-15", "Food"));

        reportService.generateMonthlyReport(
                expenses, YearMonth.of(2026, 11));

        String result = output.toString();

        assertTrue(result.contains("Total Spent: ₹1000.00"));
    }

    // Test 12: Multiple expenses with same amount
    @Test
    void testMultipleExpensesWithSameAmount() {

        List<Expense> expenses = new ArrayList<>();

        expenses.add(createExpense(
                500, "Lunch", "2026-11-05", "Food"));

        expenses.add(createExpense(
                500, "Dinner", "2026-11-06", "Food"));

        reportService.generateMonthlyReport(
                expenses, YearMonth.of(2026, 11));

        assertTrue(output.toString().contains(
                "Total Spent: ₹1000.00"));
    }
}

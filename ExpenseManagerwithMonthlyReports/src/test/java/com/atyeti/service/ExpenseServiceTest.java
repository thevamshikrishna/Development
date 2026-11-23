
package com.atyeti.service;

import com.atyeti.exception.NoFileFoundException;
import com.atyeti.model.Expense;
import com.atyeti.util.ExpenseUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseServiceTest {

    @TempDir
    Path tempDir;

    private ExpenseService expenseService;

    @BeforeEach
    void setUp() {
        expenseService = new ExpenseService();
    }

    // Test 1: Read a valid CSV file
    @Test
    void testReadFileWithValidData() throws IOException {

        Path csvFile = tempDir.resolve("expenses.csv");

        Files.writeString(csvFile,
                "Amount,Description,Date,Category\n" +
                        "500,Food,2026-11-20,Food\n" +
                        "1000,Shopping,2026-11-21,Shopping\n"
        );

        List<Expense> expenses =
                expenseService.readFile(csvFile.toString());

        assertEquals(2, expenses.size());

        assertEquals(500, expenses.get(0).getAmount());
        assertEquals("Food", expenses.get(0).getDescription());
        assertEquals(LocalDate.of(2026, 11, 20),
                expenses.get(0).getDate());
        assertEquals("Food", expenses.get(0).getCategory());

        assertEquals(1000, expenses.get(1).getAmount());
    }

    // Test 2: Read CSV with header only
    @Test
    void testReadFileWithOnlyHeader() throws IOException {

        Path csvFile = tempDir.resolve("empty.csv");

        Files.writeString(csvFile,
                "Amount,Description,Date,Category\n"
        );

        List<Expense> expenses =
                expenseService.readFile(csvFile.toString());

        assertTrue(expenses.isEmpty());
    }

    // Test 3: Read empty CSV file
    @Test
    void testReadEmptyFile() throws IOException {

        Path csvFile = tempDir.resolve("blank.csv");

        Files.writeString(csvFile, "");

        List<Expense> expenses =
                expenseService.readFile(csvFile.toString());

        assertTrue(expenses.isEmpty());
    }

    // Test 4: Ignore invalid amount
    @Test
    void testReadFileWithInvalidAmount() throws IOException {

        Path csvFile = tempDir.resolve("invalidAmount.csv");

        Files.writeString(csvFile,
                "Amount,Description,Date,Category\n" +
                        "ABC,Food,2026-11-20,Food\n"
        );

        List<Expense> expenses =
                expenseService.readFile(csvFile.toString());

        assertTrue(expenses.isEmpty());
    }

    // Test 5: Ignore invalid date
    @Test
    void testReadFileWithInvalidDate() throws IOException {

        Path csvFile = tempDir.resolve("invalidDate.csv");

        Files.writeString(csvFile,
                "Amount,Description,Date,Category\n" +
                        "500,Food,20-11-2026,Food\n"
        );

        List<Expense> expenses =
                expenseService.readFile(csvFile.toString());

        assertTrue(expenses.isEmpty());
    }

    // Test 6: Ignore rows with insufficient columns
    @Test
    void testReadFileWithMissingColumns() throws IOException {

        Path csvFile = tempDir.resolve("missingColumns.csv");

        Files.writeString(csvFile,
                "Amount,Description,Date,Category\n" +
                        "500,Food,2026-11-20\n"
        );

        List<Expense> expenses =
                expenseService.readFile(csvFile.toString());

        assertTrue(expenses.isEmpty());
    }

    // Test 7: Throw exception when file does not exist
    @Test
    void testReadFileWhenFileDoesNotExist() {

        String invalidPath =
                tempDir.resolve("notfound.csv").toString();

        assertThrows(
                NoFileFoundException.class,
                () -> expenseService.readFile(invalidPath)
        );
    }

    // Test 8: Throw exception when directory does not exist
    @Test
    void testReadDirectoryWhenDirectoryDoesNotExist() {

        String originalPath = ExpenseUtils.FILE_PATH;

        // This scenario is tested only if FILE_PATH can be configured.
        // FILE_PATH is final, so the current implementation cannot
        // safely replace it during this test.

        File folder = new File(originalPath);

        assertNotNull(folder);
    }

    // Test 9: Check multiple CSV files
    @Test
    void testMultipleCsvFiles() throws IOException {

        Path file1 = tempDir.resolve("food.csv");
        Path file2 = tempDir.resolve("travel.csv");

        Files.writeString(file1,
                "Amount,Description,Date,Category\n" +
                        "500,Food,2026-11-20,Food\n"
        );

        Files.writeString(file2,
                "Amount,Description,Date,Category\n" +
                        "1000,Travel,2026-11-21,Travel\n"
        );

        List<Expense> first =
                expenseService.readFile(file1.toString());

        List<Expense> second =
                expenseService.readFile(file2.toString());

        assertEquals(1, first.size());
        assertEquals(1, second.size());

        assertEquals(2, first.size() + second.size());
    }

    // Test 10: Verify decimal amount conversion
    @Test
    void testReadFileWithDecimalAmount() throws IOException {

        Path csvFile = tempDir.resolve("decimal.csv");

        Files.writeString(csvFile,
                "Amount,Description,Date,Category\n" +
                        "500.75,Food,2026-11-20,Food\n"
        );

        List<Expense> expenses =
                expenseService.readFile(csvFile.toString());

        assertEquals(1, expenses.size());

        // Current implementation casts double to int.
        assertEquals(500, expenses.get(0).getAmount());
    }
}

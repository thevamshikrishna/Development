package src.main.java.com.atyeti;

import src.main.java.com.atyeti.model.LogStats;
import src.main.java.com.atyeti.service.FileReaderService;
import src.main.java.com.atyeti.service.LogAnalyzerService;
import src.main.java.com.atyeti.service.LogAnalyzerTask;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static final String filepath = "C:\\Users\\VamshiNallavalli\\OneDrive - Atyeti Inc\\Desktop\\Atyeti_Vamshi_Java\\June Month\\Multi Threading\\projects\\LogFileAnalyzerSystem\\src\\main\\java\\com\\atyeti\\logs";

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        File folder = new File(filepath);
        File[] logFiles = folder.listFiles();

        if (logFiles == null || logFiles.length == 0) {
            System.out.println("No .log files found in directory: " + filepath);
            return;
        }

        LogAnalyzerService analyzer = new LogAnalyzerService(new FileReaderService());
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        List<Future<LogStats>> futureList = new ArrayList<>();

        for (File logFile : logFiles) {
            Callable<LogStats> task = new LogAnalyzerTask(logFile, analyzer);
            futureList.add(executorService.submit(task));
        }

        long grandTotalLines=0;

        for (int i = 0; i < logFiles.length; i++) {
            try {
                LogStats stats = futureList.get(i).get();
                grandTotalLines += stats.getTotalLines();
                System.out.println("Results for: " + logFiles[i].getName());
                System.out.println(stats);
                System.out.println("--------------------------------------------------");
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Failed to process " + logFiles[i].getName());
                e.printStackTrace();
            }
        }

        System.out.println("Total Lines across all files: " + grandTotalLines);

        executorService.shutdown();
        long endTime = System.nanoTime();
        long durationInMillis = (endTime - startTime) / 1_000_000;

        System.out.println("Execution time: " + durationInMillis + " ms");
    }
}

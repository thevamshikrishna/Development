package src.main.java.com.atyeti.service;

import src.main.java.com.atyeti.model.LogStats;
import src.main.java.com.atyeti.service.LogAnalyzerService;

import java.io.File;
import java.util.concurrent.Callable;

public class LogAnalyzerTask implements Callable<LogStats> {

    private final File logFile;
    private final LogAnalyzerService analyzerService;

    public LogAnalyzerTask(File logFile, LogAnalyzerService analyzerService) {
        this.logFile = logFile;
        this.analyzerService = analyzerService;
    }

    @Override
    public LogStats call() {
        System.out.println("Thread " + Thread.currentThread().getName() + " processing: " + logFile.getName());
        return analyzerService.analyze(logFile);
    }
}


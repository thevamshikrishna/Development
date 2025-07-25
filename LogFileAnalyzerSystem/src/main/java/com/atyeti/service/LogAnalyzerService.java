package src.main.java.com.atyeti.service;

import src.main.java.com.atyeti.model.LogStats;

import java.io.File;
import java.util.List;

public class LogAnalyzerService {

    private final FileReaderService readerService;

    public LogAnalyzerService(FileReaderService readerService) {
        this.readerService = readerService;
    }

    public LogStats analyze(File file) {
        List<String> lines = readerService.readFile(file);
        int errorCount = 0;
        int warnCount = 0;

        for (String line : lines) {
            if (line.contains("ERROR")) errorCount++;
            else if (line.contains("WARN")) warnCount++;
        }

        return new LogStats(lines.size(), errorCount, warnCount);
    }
}

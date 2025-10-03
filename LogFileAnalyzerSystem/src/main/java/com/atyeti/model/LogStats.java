package src.main.java.com.atyeti.model;

public class LogStats {
    private int totalLines;
    private int errorCount;
    private int warnCount;
    private long getTotalLines;

    public LogStats(int totalLines, int errorCount, int warnCount) {
        this.totalLines = totalLines;
        this.errorCount = errorCount;
        this.warnCount = warnCount;
    }

    public int getTotalLines() {
        return totalLines;
    }


    @Override
    public String toString() {
        return "Total Lines : " + totalLines + "\n" +
                "Error Count : " + errorCount + "\n" +
                "Warn Count  : " + warnCount;
    }

    // Getters if needed
}


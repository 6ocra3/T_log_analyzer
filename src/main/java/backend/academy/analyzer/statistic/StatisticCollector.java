package backend.academy.analyzer.statistic;

import backend.academy.analyzer.log.NginxLog;
import backend.academy.analyzer.statistic.metrics.FileMetric;
import backend.academy.analyzer.statistic.metrics.LogMetric;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class StatisticCollector {

    List<LogMetric> logMetrics;
    List<FileMetric> fileMetrics;

    public StatisticCollector(List<FileMetric> fileMetrics, List<LogMetric> logMetrics) {
        this.logMetrics = logMetrics;
        this.fileMetrics = fileMetrics;
    }

    public void collectFromFile(String filePath) {
        Path path = Path.of(filePath);
        fileMetrics.forEach(m -> m.processFile(path));
        try (Stream<String> lines = Files.lines(path)) {
            lines.map(line -> {
                    try {
                        return new NginxLog(line);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Некорректная строка лога: " + line);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .forEach(this::processLog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processLog(NginxLog log) {
        logMetrics.forEach(m -> m.processLog(log));
    }

    public void showStatistic(){
        fileMetrics.forEach(FileMetric::showStatistic);
        logMetrics.forEach(LogMetric::showStatistic);
    }
}

package backend.academy.analyzer.statistic;

import backend.academy.analyzer.log.NginxLog;
import backend.academy.analyzer.statistic.metrics.BaseMetric;
import backend.academy.analyzer.statistic.metrics.ResponseCodeMetric;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class StatisticCollector {

    List<BaseMetric> metrics;

    public StatisticCollector(List<BaseMetric> metrics) {
        this.metrics = metrics;
    }

    public void collectFromFile(String filePath) {
        Path path = Path.of(filePath);
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
        metrics.forEach(m -> m.processLog(log));
    }

    public void showStatistic(){
        metrics.forEach(BaseMetric::showStatistic);
    }
}

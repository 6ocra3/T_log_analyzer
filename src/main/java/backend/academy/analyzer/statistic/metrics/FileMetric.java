package backend.academy.analyzer.statistic.metrics;

import java.nio.file.Path;

public interface FileMetric {
    abstract public void processFile(Path path);
    abstract public void showStatistic();
}

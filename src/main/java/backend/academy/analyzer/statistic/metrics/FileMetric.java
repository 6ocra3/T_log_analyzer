package backend.academy.analyzer.statistic.metrics;

import backend.academy.analyzer.visualizer.Visualizer;
import java.nio.file.Path;

public interface FileMetric {
    void processFile(Path path);

    String getStatistic(Visualizer visualizer);
}

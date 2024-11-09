package backend.academy.analyzer.statistic.metrics;

import backend.academy.analyzer.log.NginxLog;
import backend.academy.analyzer.visualizer.Visualizer;

public interface LogMetric {
    void processLog(NginxLog log);
    String getStatistic(Visualizer visualizer);
}

package backend.academy.analyzer.statistic.metrics;

import backend.academy.analyzer.log.NginxLog;

public interface LogMetric {
    abstract public void processLog(NginxLog log);
    abstract public void showStatistic();
}

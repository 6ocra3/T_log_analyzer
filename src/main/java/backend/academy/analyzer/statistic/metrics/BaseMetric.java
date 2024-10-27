package backend.academy.analyzer.statistic.metrics;

import backend.academy.analyzer.log.NginxLog;

public abstract class BaseMetric {
    abstract public void processLog(NginxLog log);
    abstract public void showStatistic();
}

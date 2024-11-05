package backend.academy.analyzer;

import backend.academy.analyzer.config.AnalyzerConfig;
import backend.academy.analyzer.statistic.StatisticCollector;
import backend.academy.analyzer.statistic.metrics.CommonMetric;
import backend.academy.analyzer.statistic.metrics.FileMetric;
import backend.academy.analyzer.statistic.metrics.LogMetric;
import backend.academy.analyzer.statistic.metrics.ResponseCodeMetric;
import backend.academy.analyzer.statistic.metrics.ResponseSizeMetric;
import java.util.Arrays;
import java.util.List;

public class Analyzer {

    CommonMetric commonMetric = new CommonMetric();
    List<LogMetric> logMetrics = List.of(new ResponseSizeMetric(), new ResponseCodeMetric());
    List<FileMetric> fileMetrics = List.of(commonMetric);

    public Analyzer(AnalyzerConfig config) {
        String[] filesForRead = new String[] {"./logs_examples/logs_27-10-24.txt"};

        StatisticCollector collector = new StatisticCollector(fileMetrics, logMetrics);
        Arrays.stream(filesForRead).forEach(collector::collectFromFile);
        collector.showStatistic();
    }

}

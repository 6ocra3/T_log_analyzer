package backend.academy.analyzer;

import backend.academy.analyzer.config.AnalyzerConfig;
import backend.academy.analyzer.statistic.StatisticCollector;
import backend.academy.analyzer.statistic.metrics.BaseMetric;
import backend.academy.analyzer.statistic.metrics.ResponseCodeMetric;
import java.util.Arrays;
import java.util.List;

public class Analyzer {

    List<BaseMetric> metrics = List.of(new ResponseCodeMetric());

    public Analyzer(AnalyzerConfig config) {
        String[] filesForRead = new String[] {"./logs_examples/logs_27-10-24.txt"};

        StatisticCollector collector = new StatisticCollector(metrics);
        Arrays.stream(filesForRead).forEach(collector::collectFromFile);
        collector.showStatistic();
    }

}

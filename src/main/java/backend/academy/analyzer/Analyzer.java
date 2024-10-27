package backend.academy.analyzer;

import backend.academy.analyzer.config.AnalyzerConfig;
import backend.academy.analyzer.statistic.StatisticCollector;
import java.util.Arrays;

public class Analyzer {

    public Analyzer(AnalyzerConfig config) {
        String[] filesForRead = new String[] {"./logs_examples/logs_27-10-24.txt"};

        StatisticCollector collector = new StatisticCollector();
        Arrays.stream(filesForRead).forEach(collector::collectFromFile);
    }

}

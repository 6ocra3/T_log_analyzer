package backend.academy.analyzer;

import backend.academy.analyzer.config.AnalyzerConfig;
import backend.academy.analyzer.statistic.StatisticCollector;
import backend.academy.analyzer.statistic.metrics.CommonMetric;
import backend.academy.analyzer.statistic.metrics.FileMetric;
import backend.academy.analyzer.statistic.metrics.LogMetric;
import backend.academy.analyzer.statistic.metrics.RequestTargetMetric;
import backend.academy.analyzer.statistic.metrics.ResponseCodeMetric;
import backend.academy.analyzer.statistic.metrics.ResponseSizeMetric;
import backend.academy.analyzer.statistic.metrics.TimeMetric;
import backend.academy.analyzer.utils.FilesPatternMatcher;
import backend.academy.analyzer.visualizer.AdocVisualizer;
import backend.academy.analyzer.visualizer.MDVisualizer;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Analyzer {

    private final CommonMetric commonMetric = new CommonMetric();
    private final List<LogMetric> logMetrics = List.of(new ResponseSizeMetric(),
        new RequestTargetMetric(), new ResponseCodeMetric(), new TimeMetric());
    private final List<FileMetric> fileMetrics = List.of(commonMetric);
    private final StatisticCollector collector = new StatisticCollector(fileMetrics, logMetrics);
    private final AnalyzerConfig config;


    public Analyzer(AnalyzerConfig config) {
        this.config = config;
        commonMetric.config(config);
        collector.config(config);
        useFormat();
        usePath();
        collector.showStatistic();
    }

    private void useFormat() {
        switch (config.format()) {
            case "markdown":
                collector.visualizer(new MDVisualizer());
                break;
            default:
                collector.visualizer(new AdocVisualizer());
        }
    }

    private void usePath() {
        String pattern = config.path();
        if (isUrl(pattern)) {
            useRemoteFiles(pattern);
        } else {
            useLocalFiles(pattern);
        }
    }

    private void useRemoteFiles(String inputFile) {
        System.setProperty("https.protocols", "TLSv1.2");
        try {
            collector.collectFromHttp(inputFile);
        } catch (IOException e) {
            log.error(String.valueOf(e));
        }

    }

    private void useLocalFiles(String inputFile) {
        Path cur = Paths.get("").toAbsolutePath();
        List<String> filesForRead = getPathFromPattern(cur, inputFile);
        filesForRead.forEach(collector::collectFromFile);
    }

    public static List<String> getPathFromPattern(Path rootDir, String pattern) {
        String formatedPattern = "glob:" + pattern;
        List<String> files = new ArrayList<>();
        try {
            files = FilesPatternMatcher.searchWithPattern(rootDir, formatedPattern);
        } catch (IOException e) {
            log.error(String.valueOf(e));
        }
        return files;
    }

    private boolean isUrl(String input) {
        return input.startsWith("http://") || input.startsWith("https://") || input.startsWith("ftp://");
    }

}

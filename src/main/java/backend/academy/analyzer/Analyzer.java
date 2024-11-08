package backend.academy.analyzer;

import backend.academy.analyzer.config.AnalyzerConfig;
import backend.academy.analyzer.statistic.StatisticCollector;
import backend.academy.analyzer.statistic.metrics.CommonMetric;
import backend.academy.analyzer.statistic.metrics.FileMetric;
import backend.academy.analyzer.statistic.metrics.LogMetric;
import backend.academy.analyzer.statistic.metrics.RequestTargetMetric;
import backend.academy.analyzer.statistic.metrics.ResponseCodeMetric;
import backend.academy.analyzer.statistic.metrics.ResponseSizeMetric;
import backend.academy.analyzer.utils.FilesPatternMatcher;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Analyzer {

    CommonMetric commonMetric = new CommonMetric();
    List<LogMetric> logMetrics = List.of(new ResponseSizeMetric(), new RequestTargetMetric(),  new ResponseCodeMetric());
    List<FileMetric> fileMetrics = List.of(commonMetric);
    StatisticCollector collector = new StatisticCollector(fileMetrics, logMetrics);

    public Analyzer(AnalyzerConfig config) {
        commonMetric.config(config);
        String pattern = "**/logs_27-10-24.txt";
        useLocalFiles(pattern);
        collector.showStatistic();
    }

    private void useLocalFiles(String pattern){
        List<String> filesForRead = getPathFromPattern(pattern);
        filesForRead.forEach(collector::collectFromFile);
    }

    private List<String> getPathFromPattern(String pattern) {
        Path cur = Paths.get("").toAbsolutePath();
        String formatedPattern = "glob:"+pattern;
        List<String> files = new ArrayList<>();
        try{
            files = FilesPatternMatcher.searchWithPattern(cur, formatedPattern);
        } catch (IOException e){
            e.printStackTrace();
        }
        return files;
    }

}

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
import backend.academy.analyzer.visualizer.AdocVisualizer;
import backend.academy.analyzer.visualizer.MDVisualizer;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Analyzer {

    CommonMetric commonMetric = new CommonMetric();
    List<LogMetric> logMetrics = List.of(new ResponseSizeMetric(), new RequestTargetMetric(), new ResponseCodeMetric());
    List<FileMetric> fileMetrics = List.of(commonMetric);
    StatisticCollector collector = new StatisticCollector(fileMetrics, logMetrics);
    AnalyzerConfig config;

    public Analyzer(AnalyzerConfig config) {
        this.config = config;
        commonMetric.config(config);
        collector.config(config);
        useFormat();
        usePath();
        collector.showStatistic();
    }

    private void useFormat(){
        switch (config.format()){
            case "markdown":
                collector.visualizer(new MDVisualizer());
                break;
            default:
                collector.visualizer(new AdocVisualizer());
        }
    }

    private void usePath(){
        String pattern = config.path();
        if(isUrl(pattern)){
            useRemoteFiles(pattern);
        } else{
            useLocalFiles(pattern);
        }
    }

    private void useRemoteFiles(String inputFile){
        System.setProperty("https.protocols", "TLSv1.2");
        try{
            collector.collectFromHttp(inputFile);
        } catch (IOException e){
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return files;
    }

    private boolean isUrl(String input) {
        return input.startsWith("http://") || input.startsWith("https://") || input.startsWith("ftp://");
    }

}

package backend.academy.samples.statisticTests;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import backend.academy.analyzer.config.AnalyzerConfig;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import backend.academy.analyzer.statistic.StatisticCollector;
import backend.academy.analyzer.statistic.metrics.LogMetric;
import backend.academy.analyzer.log.NginxLog;
import backend.academy.analyzer.visualizer.Visualizer;

public class StatisticCollectorTest extends BaseStatisticTest {
    @Test
    public void collectFromHttpTest(){
        // Arrange
        LogMetric mockLogMetric = mock(LogMetric.class);
        List<LogMetric> logMetrics = new ArrayList<>();
        logMetrics.add(mockLogMetric);
        AnalyzerConfig config = new AnalyzerConfig();
        String filePath = baseDir + "/" + firstFileName;

        StatisticCollector collector = new StatisticCollector(new ArrayList<>(), logMetrics);
        collector.config(config);

        // Act
        collector.collectFromFile(filePath);

        // Assert
        verify(mockLogMetric, atLeastOnce()).processLog(any(NginxLog.class));
    }
}

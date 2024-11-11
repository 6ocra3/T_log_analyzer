package backend.academy.samples.statisticTests;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import backend.academy.analyzer.config.AnalyzerConfig;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import backend.academy.analyzer.statistic.StatisticCollector;
import backend.academy.analyzer.statistic.metrics.LogMetric;
import backend.academy.analyzer.log.NginxLog;
import backend.academy.analyzer.visualizer.Visualizer;
import org.mockito.ArgumentCaptor;

public class StatisticCollectorTest extends BaseStatisticTest {
    @Test
    public void collectFromHttpTest() {
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

    @Test
    public void filterByDateTest() {
        // Arrange
        Stream<String> logs = getTestLogStream();
        LogMetric mockLogMetric = mock(LogMetric.class);
        List<LogMetric> logMetrics = new ArrayList<>();
        AnalyzerConfig config = new AnalyzerConfig();
        LocalDateTime filterDate = LocalDateTime.of(2023, 3, 1, 0, 0);
        config.searchPeriodFrom(filterDate);
        logMetrics.add(mockLogMetric);

        StatisticCollector collector = new StatisticCollector(new ArrayList<>(), logMetrics);
        collector.config(config);

        // Act
        collector.processLogStream(logs);

        // Assert
        ArgumentCaptor<NginxLog> logCaptor = ArgumentCaptor.forClass(NginxLog.class);
        verify(mockLogMetric, atLeastOnce()).processLog(logCaptor.capture());

        List<NginxLog> processedLogs = logCaptor.getAllValues();
        assertEquals(3, processedLogs.size());

        processedLogs.forEach(log ->
            assertTrue(log.dateTime().isAfter(filterDate) || log.dateTime().isEqual(filterDate))
        );
    }

    private Stream<String> getTestLogStream() {
        List<String> logLines = List.of(
            "192.168.0.1 - - [01/Jan/2023:12:00:00 +0000] \"GET /index.html HTTP/1.1\" 200 1024 \"-\" \"Mozilla/5.0\"",
            "192.168.0.2 - - [02/Feb/2023:15:30:00 +0000] \"POST /submit HTTP/1.1\" 404 512 \"https://example.com\" \"Mozilla/5.0\"",
            "192.168.0.3 - - [03/Mar/2023:18:45:00 +0000] \"PUT /api/data HTTP/1.1\" 201 2048 \"https://example.com/ref\" \"Mozilla/5.0\"",
            "192.168.0.4 - - [04/Apr/2023:10:15:00 +0000] \"DELETE /api/data/123 HTTP/1.1\" 500 128 \"-\" \"Mozilla/5.0\"",
            "192.168.0.5 - - [05/May/2023:20:00:00 +0000] \"GET /contact HTTP/1.1\" 301 256 \"https://example.com/contact\" \"Mozilla/5.0\""
        );

        return logLines.stream();
    }
}

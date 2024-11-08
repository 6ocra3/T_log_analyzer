package backend.academy.analyzer.statistic;

import backend.academy.analyzer.log.NginxLog;
import backend.academy.analyzer.statistic.metrics.FileMetric;
import backend.academy.analyzer.statistic.metrics.LogMetric;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class StatisticCollector {

    List<LogMetric> logMetrics;
    List<FileMetric> fileMetrics;

    public StatisticCollector(List<FileMetric> fileMetrics, List<LogMetric> logMetrics) {
        this.logMetrics = logMetrics;
        this.fileMetrics = fileMetrics;
    }

    public void collectFromHttp(String fileUrl) throws IOException {
        URI uri = URI.create(fileUrl);
        URL url = uri.toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IOException("Failed to download file: HTTP error code " + connection.getResponseCode());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        Stream<String> logs = reader.lines();
        processLogStream(logs);
    }

    public void collectFromFile(String filePath) {
        Path path = Path.of(filePath);
        fileMetrics.forEach(m -> m.processFile(path));
        Stream<String> logs = Stream.empty();
        try {
            logs = Files.lines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        processLogStream(logs);
    }

    public void processLogStream(Stream<String> logs) {
        logs.map(line -> {
                try {
                    return new NginxLog(line);
                } catch (IllegalArgumentException e) {
                    System.err.println("Некорректная строка лога: " + line);
                    return null;
                }
            })
            .filter(Objects::nonNull)
            .forEach(this::processLog);
    }

    private void processLog(NginxLog log) {
        logMetrics.forEach(m -> m.processLog(log));
    }

    public void showStatistic() {
        fileMetrics.forEach(FileMetric::showStatistic);
        logMetrics.forEach(LogMetric::showStatistic);
    }
}

package backend.academy.analyzer.statistic;

import backend.academy.analyzer.log.NginxLog;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Stream;

public class StatisticCollector {

    public StatisticCollector() {
    }

    ;

    public void collectFromFile(String filePath) {
        Path path = Path.of(filePath);
        try (Stream<String> lines = Files.lines(path)) {
            lines.map(line -> {
                    try {
                        return new NginxLog(line);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Некорректная строка лога: " + line);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .forEach(this::processLog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processLog(NginxLog log) {
        System.out.println(log.statusCode());
    }
}

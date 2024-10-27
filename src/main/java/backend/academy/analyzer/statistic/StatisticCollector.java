package backend.academy.analyzer.statistic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class StatisticCollector {

    public StatisticCollector(){};

    public void collectFromFile(String filePath) {
        Path path = Path.of(filePath);
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

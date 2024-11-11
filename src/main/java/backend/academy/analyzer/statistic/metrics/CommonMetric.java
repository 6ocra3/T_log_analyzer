package backend.academy.analyzer.statistic.metrics;

import backend.academy.analyzer.config.AnalyzerConfig;
import backend.academy.analyzer.visualizer.Visualizer;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Getter;
import lombok.Setter;

@Getter
public class CommonMetric implements FileMetric {
    private final List<String> files = new ArrayList<>();
    @Setter
    private AnalyzerConfig config;

    @Override
    public void processFile(Path path) {
        files.add(String.valueOf(path.getFileName()));
    }

    @Override
    public String getStatistic(Visualizer visualizer) {
        List<String> headers = List.of("Параметр", "Значения");
        List<String> col1 = List.of("Файлы", "Начальная дата", "Конечная дата", "Формат");
        String startDate = config.searchPeriodFrom() == null ? "-" : config.searchPeriodFrom().toString();
        String endDate = config.searchPeriodTo() == null ? "-" : config.searchPeriodTo().toString();
        String formatString = config.format() == null ? "-" : config.format();
        List<String> col2 = new ArrayList<>(List.of(files.toString(), startDate, endDate, formatString));

        // Таблица 2х4 в таблицу 4х2
        List<List<String>> table = IntStream.range(0, col1.size())
            .mapToObj(i -> List.of(col1.get(i), col2.get(i)))
            .collect(Collectors.toList());

        return visualizer.showTable(headers, table, "Основная статистика");
    }
}

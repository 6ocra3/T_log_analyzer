package backend.academy.analyzer.statistic.metrics;

import backend.academy.analyzer.log.NginxLog;
import backend.academy.analyzer.visualizer.Visualizer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseCodeMetric implements LogMetric {
    private final Map<HttpStatus, Integer> codesCounter = new HashMap<>();

    @Override
    public void processLog(NginxLog log) {
        codesCounter.put(log.statusCode(), codesCounter.getOrDefault(log.statusCode(), 0) + 1);
    }

    @Override
    public String getStatistic(Visualizer visualizer) {
        List<Map.Entry<HttpStatus, Integer>> sortedCountedCodes = new ArrayList<>(codesCounter.entrySet());
        sortedCountedCodes.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        List<String> headers = List.of("Код", "Расшифровка", "Количество");
        List<List<String>> table = new ArrayList<>();

        for (Map.Entry<HttpStatus, Integer> code : sortedCountedCodes) {
            table.add(List.of(String.valueOf(code.getKey().value()), code.getKey().getReasonPhrase(),
                String.valueOf(code.getValue())));
        }

        return visualizer.showTable(headers, table, "Статистика кодов ответа");
    }

}

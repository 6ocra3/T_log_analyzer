package backend.academy.analyzer.statistic.metrics;

import backend.academy.analyzer.log.NginxLog;
import backend.academy.analyzer.visualizer.Visualizer;
import lombok.Getter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class RequestTargetMetric implements LogMetric {
    private static final int TARGET_COUNTS = 5;
    private int totalCount = 0;
    private final Map<String, Integer> targetCounter = new HashMap<>();


    @Override

    public void processLog(NginxLog log) {
        totalCount++;
        String[] pathElements = log.requestPath().split("/");
        String pathTarget = pathElements[pathElements.length-1];
        targetCounter.put(pathTarget, targetCounter.getOrDefault(pathTarget, 0)+1);
    }

    @Override
    public String getStatistic(Visualizer visualizer) {
        List<Map.Entry<String, Integer>> sortedCounts = new ArrayList<>(targetCounter.entrySet());
        sortedCounts.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        List<String> headers = List.of("Ресурс", "Количество");
        List<List<String>> table = new ArrayList<>();
        table.add(List.of("Всего запросов", String.valueOf(totalCount)));

        for(int i = 0; i<Math.min(TARGET_COUNTS, sortedCounts.size());i++){
            Map.Entry<String, Integer> entry = sortedCounts.get(i);
            table.add(List.of(entry.getKey(), String.valueOf(entry.getValue())));
        }

        return visualizer.showTable(headers, table, "Статистика самых запрашиваемых ресурсов");
    }
}

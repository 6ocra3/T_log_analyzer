package backend.academy.analyzer.statistic.metrics;

import backend.academy.analyzer.log.NginxLog;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void showStatistic() {
        System.out.println("Всего запросов: " + totalCount);
        List<Map.Entry<String, Integer>> sortedCounts = new ArrayList<>(targetCounter.entrySet());
        sortedCounts.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        for(int i = 0; i<Math.min(TARGET_COUNTS, sortedCounts.size());i++){
            Map.Entry<String, Integer> entry = sortedCounts.get(i);
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        System.out.println();
    }
}

package backend.academy.analyzer.statistic.metrics;

import backend.academy.analyzer.log.HttpResponseCode;
import backend.academy.analyzer.log.NginxLog;
import backend.academy.analyzer.visualizer.Visualizer;
import com.google.common.collect.Table;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseCodeMetric implements LogMetric {
    Map<HttpResponseCode, Integer> codesCounter = new HashMap<>();

    public ResponseCodeMetric(){}

    @Override
    public void processLog(NginxLog log) {
        codesCounter.put(log.statusCode(), codesCounter.getOrDefault(log.statusCode(), 0)+1);
    }

    @Override
    public String getStatistic(Visualizer visualizer) {
        List<Map.Entry<HttpResponseCode, Integer>> sortedCountedCodes = new ArrayList<>(codesCounter.entrySet());
        sortedCountedCodes.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        List<String> headers = List.of("Код", "Расшифровка", "Количество");
        List<List<String>> table = new ArrayList<>();

        for(Map.Entry<HttpResponseCode, Integer> code : sortedCountedCodes){
            table.add(List.of(String.valueOf(code.getKey().code()), code.getKey().codeName(), String.valueOf(code.getValue())));
        }

        return visualizer.showTable(headers, table, "Статистика кодов ответа");
    }

}

package backend.academy.analyzer.statistic.metrics;

import backend.academy.analyzer.log.HttpResponseCode;
import backend.academy.analyzer.log.NginxLog;
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
    public void showStatistic() {
        System.out.println("Статистика кодов ответа");
        List<Map.Entry<HttpResponseCode, Integer>> sortedCountedCodes = new ArrayList<>(codesCounter.entrySet());
        sortedCountedCodes.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        for(Map.Entry<HttpResponseCode, Integer> code : sortedCountedCodes){
            System.out.println(code.getKey().code() + " " + code.getKey().codeName() + " " + code.getValue());
        }
        System.out.println();
    }

}

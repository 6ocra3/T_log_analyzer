package backend.academy.analyzer.statistic.metrics;

import backend.academy.analyzer.log.NginxLog;
import backend.academy.analyzer.visualizer.Visualizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResponseSizeMetric implements LogMetric {

    List<Integer> values = new ArrayList<>();

    private int getPercentileValue(int percentile) {
        Collections.sort(values);
        int index = (int) Math.ceil((double) percentile / 100 * values.size()) - 1;
        if(index < 0 || index >= values.size()) return 0;
        return values.get(index);
    }

    private long getMiddleValue() {
        if(values.isEmpty()){
            return 0;
        }
        long sum = 0;
        for (Integer number : values) {
            if(number < 0){
                System.out.println(1);
                continue;
            }
            sum += number;
        }
        return sum / values.size();
    }

    @Override
    public void processLog(NginxLog log) {
        values.add(log.responseSize());
    }

    @Override
    public String getStatistic(Visualizer visualizer) {
        List<String> headers = List.of("Параметр", "Значения");
        List<String> col1 = List.of("Средний размер ответа", "25 perc","50perc","75perc","95perc");
        List<String> col2 = List.of(String.valueOf(getMiddleValue()), String.valueOf(getPercentileValue(25)),
            String.valueOf(getPercentileValue(50)), String.valueOf(getPercentileValue(75)),
            String.valueOf(getPercentileValue(95)));

        // Таблица 2х4 в таблицу 4х2
        List<List<String>> table = IntStream.range(0, col1.size())
            .mapToObj(i -> List.of(col1.get(i), col2.get(i)))
            .collect(Collectors.toList());

        return visualizer.showTable(headers, table, "Статистика размеров ответа");
    }
}

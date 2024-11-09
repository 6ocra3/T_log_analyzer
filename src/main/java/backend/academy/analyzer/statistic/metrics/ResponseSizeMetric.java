package backend.academy.analyzer.statistic.metrics;

import backend.academy.analyzer.log.NginxLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public void showStatistic() {
        System.out.println("Статистика размеров ответа");
        System.out.println("Средний размер ответа: " + getMiddleValue());
        System.out.println("25p размера ответа: " + getPercentileValue(25));
        System.out.println("50p размера ответа: " + getPercentileValue(50));
        System.out.println("75p размера ответа: " + getPercentileValue(75));
        System.out.println("95p размера ответа: " + getPercentileValue(95));
        System.out.println();
    }
}

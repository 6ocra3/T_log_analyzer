package backend.academy.analyzer.statistic.metrics;

import backend.academy.analyzer.log.NginxLog;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CommonMetric implements FileMetric, LogMetric {
    List<Integer> values = new ArrayList<>();
    List<String> files = new ArrayList<>();
    Integer totalCount = 0;

    @Override
    public void processFile(Path path) {
        files.add(String.valueOf(path.getFileName()));
    }

    @Override
    public void processLog(NginxLog log) {
        totalCount++;
        values.add(log.responseSize());
    }

    private int getMiddleValue(){
        int sum = 0;
        for (Integer number : values) {
            sum += number;
        }
        return sum;
    }

    private int get95PValue(){
        Collections.sort(values);
        int index = (int) Math.ceil(0.95 * values.size()) - 1;
        return values.get(index);
    }

    @Override
    public void showStatistic() {
        System.out.println("Файлы " + files.toString());
        System.out.println("Количество запросов " + totalCount);

        System.out.println("Средний размер ответа " + getMiddleValue());
        System.out.println("95p размера ответа " + get95PValue());
    }
}

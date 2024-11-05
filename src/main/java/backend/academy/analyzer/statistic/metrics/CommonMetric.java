package backend.academy.analyzer.statistic.metrics;

import backend.academy.analyzer.log.NginxLog;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CommonMetric implements FileMetric {
    List<String> files = new ArrayList<>();

    @Override
    public void processFile(Path path) {
        files.add(String.valueOf(path.getFileName()));
    }

    @Override
    public void showStatistic() {
        System.out.println("Файлы " + files.toString());
        System.out.println();
    }
}

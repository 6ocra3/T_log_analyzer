package backend.academy.analyzer.statistic.metrics;

import backend.academy.analyzer.config.AnalyzerConfig;
import backend.academy.analyzer.log.NginxLog;
import lombok.Getter;
import lombok.Setter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CommonMetric implements FileMetric {
    List<String> files = new ArrayList<>();
    @Setter @Getter
    private AnalyzerConfig config;

    @Override
    public void processFile(Path path) {
        files.add(String.valueOf(path.getFileName()));
    }

    @Override
    public void showStatistic() {
        System.out.println("Общая статистика");
        System.out.println("Файлы " + files.toString());
        if(config != null){
            System.out.println("Начальная дата " + config.searchPeriodFrom());
            System.out.println("Конечная дата " + config.searchPeriodTo());
            System.out.println("Формат " + config.format());
        }

        System.out.println();
    }
}

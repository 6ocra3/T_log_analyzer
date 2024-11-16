package backend.academy.analyzer.statistic.metrics;

import backend.academy.analyzer.log.NginxLog;
import backend.academy.analyzer.visualizer.Visualizer;
import java.util.List;

public class TimeMetric implements LogMetric {
    private final static int NANO_IN_MILLI = 1_000_000;
    private Long initTime;
    private Long prevTime;
    private Long allPeriods = 0L;
    private Long counts = 0L;

    @Override
    public void processLog(NginxLog log) {
        if (initTime == null) {
            initTime = System.nanoTime();
        }
        if (prevTime == null) {
            prevTime = System.nanoTime();
        }
        long curTime = System.nanoTime();
        allPeriods += (curTime - prevTime);
        counts++;
        prevTime = curTime;
    }

    @Override
    public String getStatistic(Visualizer visualizer) {
        String onOne = allPeriods / counts + " наносекунд";
        String total = (prevTime - initTime) / NANO_IN_MILLI + " миллисекунд";

        List<String> headers = List.of("Параметр", "Значения");
        List<List<String>> values = List.of(List.of("Среднее время на один лог", onOne),
            List.of("Время на все логи", total));

        return visualizer.showTable(headers, values, "Временная статистика");
    }
}

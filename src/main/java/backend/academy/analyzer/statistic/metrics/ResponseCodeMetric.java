package backend.academy.analyzer.statistic.metrics;

import backend.academy.analyzer.log.HttpResponseCode;
import backend.academy.analyzer.log.NginxLog;
import java.util.HashMap;
import java.util.Map;

public class ResponseCodeMetric extends BaseMetric {
    Map<HttpResponseCode, Integer> codesCounter = new HashMap<>();

    public ResponseCodeMetric(){}

    @Override
    public void processLog(NginxLog log) {
        codesCounter.put(log.statusCode(), codesCounter.getOrDefault(log.statusCode(), 0)+1);
    }

    @Override
    public void showStatistic() {
        for(Map.Entry<HttpResponseCode, Integer> code : codesCounter.entrySet()){
            System.out.println(code.getKey().code() + " " + code.getKey().codeName() + " " + code.getValue());
        }
    }

}

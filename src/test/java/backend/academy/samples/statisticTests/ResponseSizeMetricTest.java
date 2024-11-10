package backend.academy.samples.statisticTests;

import backend.academy.analyzer.log.NginxLog;
import backend.academy.analyzer.statistic.metrics.ResponseSizeMetric;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseSizeMetricTest {

    @Test
    public void processLogTest(){
        // Arrange
        ResponseSizeMetric metric = new ResponseSizeMetric();
        String example =
            "65.23.86.144 - - [27/Oct/2024:16:08:06 +0000] \"GET /Ameliorated_complexity_Synergistic.css HTTP/1.1\" 200 2098 \"-\" \"Opera/10.44 (X11; Linux x86_64; en-US) Presto/2.12.213 Version/12.00\"";
        NginxLog log = new NginxLog(example);

        // Act
        metric.processLog(log);

        // Assert
        assertEquals(metric.values().size(), 1);
        assertEquals(metric.values().getFirst(), log.responseSize());
    }
}

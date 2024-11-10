package backend.academy.samples.statisticTests;

import backend.academy.analyzer.statistic.metrics.CommonMetric;
import org.junit.jupiter.api.Test;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommonMetricTest extends BaseStatisticTest{
    @Test
    public void testProcessFile() throws URISyntaxException {
        // Arrange
        CommonMetric metric = new CommonMetric();
        Path path = Paths.get(baseDir+"/"+firstFileName);

        // Act
        metric.processFile(path);

        // Assert
        assertEquals(metric.files().size(), 1);
        assertEquals(metric.files().getFirst(), String.valueOf(path.getFileName()));

    }
}

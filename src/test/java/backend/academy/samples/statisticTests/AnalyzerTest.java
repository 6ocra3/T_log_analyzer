package backend.academy.samples.statisticTests;

import backend.academy.analyzer.Analyzer;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnalyzerTest extends BaseStatisticTest {

    @Test
    public void getPathFromPatternTest() {
        // Arrange
        Path cur = Paths.get(baseDir).toAbsolutePath();
        String pattern = "log*";

        // Act
        List<String> filesForRead = Analyzer.getPathFromPattern(cur, pattern);

        // Assert
        assertEquals(2, filesForRead.size());
    }
}

package backend.academy.samples.statisticTests;

import backend.academy.analyzer.Analyzer;
import org.junit.jupiter.api.Test;

public class AnalyzerTest extends BaseStatisticTest{

    @Test
    public void getPathFromPatternTest(){
        List<String> ans = Analyzer.getPathFromPattern("**/log*");
    }
}

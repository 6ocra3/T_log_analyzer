package backend.academy.samples;

import backend.academy.analyzer.visualizer.MDVisualizer;
import backend.academy.analyzer.visualizer.Visualizer;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MDVisualizerTest {
    private static final String TABLE_EXAMPLE = """
        ## t1
        |h1|h2|
        |:----:|:----:|
        |v1|v2|
        |v3|v4|
        """;

    @Test
    public void showTableTest(){
        List<String> headers = List.of("h1", "h2");
        List<List<String>> values = List.of(List.of("v1", "v2"), List.of("v3", "v4"));
        String title = "t1";
        Visualizer visualizer = new MDVisualizer();
        String table = visualizer.showTable(headers, values,title);
        assertEquals(TABLE_EXAMPLE, table);
    }
}

package backend.academy.analyzer.visualizer;

import java.util.List;

public interface Visualizer {
    String showTitle(String title);
    String showTable(List<String> headers, List<List<String>> values, String tableTitle);
}

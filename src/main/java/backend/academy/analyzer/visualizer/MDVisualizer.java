package backend.academy.analyzer.visualizer;

import java.util.Collections;
import java.util.List;

public class MDVisualizer implements Visualizer {

    @Override
    public String showTitle(String title) {
        return "## " + title + "\n";
    }

    @Override
    public String showTable(List<String> headers, List<List<String>> values, String tableTitle) {
        List<String> options = Collections.nCopies(headers.size(), ":----:");
        StringBuilder table = new StringBuilder();
        table.append(showTitle(tableTitle));
        table.append(getTableLine(headers));
        table.append(getTableLine(options));
        for(List<String> row : values){
            table.append(getTableLine(row));
        }
        return table.toString();
    }

    private String getTableLine(List<String> line){
        StringBuilder sb = new StringBuilder();
        sb.append("|");
        for(String l : line){
            sb.append(l).append("|");
        }
        sb.append("\n");
        return sb.toString();

    }
}

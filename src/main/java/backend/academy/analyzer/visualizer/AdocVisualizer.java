package backend.academy.analyzer.visualizer;

import java.util.Collections;
import java.util.List;

public class AdocVisualizer implements Visualizer{

    @Override
    public String showTitle(String title) {
        return "== " + title + "\n";
    }

    @Override
    public String showTable(List<String> headers, List<List<String>> values, String tableTitle) {
        StringBuilder table = new StringBuilder();
        table.append(showTitle(tableTitle));
        table.append(getOptionsLine(headers.size()));
        String tableStartEnd = "|===\n";
        table.append(tableStartEnd);
        table.append(getTableLine(headers));
        for(List<String> row : values){
            table.append(getTableLine(row));
        }
        table.append(tableStartEnd);
        return table.toString();
    }

    private String getOptionsLine(int numCols){
        return "[cols=\"" + "^" + ",^".repeat(Math.max(0, numCols - 1)) + "\", options=\"header\"]\n";
    }

    private String getTableLine(List<String> line){
        StringBuilder sb = new StringBuilder();
        for(String l : line){
            sb.append("|").append(l);
        }
        sb.append("\n");
        return sb.toString();

    }
}

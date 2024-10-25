package backend.academy;

import backend.academy.analyzer.Analyzer;
import backend.academy.analyzer.config.AnalyzerConfig;
import com.beust.jcommander.JCommander;
import lombok.experimental.UtilityClass;
import java.util.Arrays;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        String[] mockArgs = {"--path", "test"};
        AnalyzerConfig config = new AnalyzerConfig();
        JCommander jargs = JCommander.newBuilder()
            .addObject(config)
            .build();
        jargs.parse(mockArgs);
        Analyzer analyzer = new Analyzer(config);
    }
}

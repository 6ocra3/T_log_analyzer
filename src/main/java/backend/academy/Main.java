package backend.academy;

import backend.academy.analyzer.Analyzer;
import backend.academy.analyzer.config.AnalyzerConfig;
import com.beust.jcommander.JCommander;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        String[] mockArgs = {"--path",
//            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs",
            "**/logs*",
            "--from", "2015-05-21", "--format", "markdown", "--filter-value", "hello"};
        AnalyzerConfig config = new AnalyzerConfig();
        JCommander jargs = JCommander.newBuilder()
            .addObject(config)
            .build();
        jargs.parse(args);
        Analyzer analyzer = new Analyzer(config);
    }
}

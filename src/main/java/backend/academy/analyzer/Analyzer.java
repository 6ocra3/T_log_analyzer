package backend.academy.analyzer;

import backend.academy.analyzer.config.AnalyzerConfig;
import com.beust.jcommander.JCommander;

public class Analyzer {

    public Analyzer(AnalyzerConfig config){
        System.out.println(config.path());
    }

}

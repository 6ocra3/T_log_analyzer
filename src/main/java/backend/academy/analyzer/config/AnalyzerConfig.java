package backend.academy.analyzer.config;

import backend.academy.analyzer.converters.InstantConverter;
import com.beust.jcommander.Parameter;
import lombok.Getter;
import java.time.Instant;

@Getter
public class AnalyzerConfig {
    @Parameter(
        names = {"--path"},
        description = "Path to your log directory",
        required = true
    )
    private String path;

    @Parameter(
        names = {"--from"},
        description = "Start of period",
        converter = InstantConverter.class
    )
    private Instant searchPeriodFrom;

    @Parameter(
        names = {"--to"},
        description = "Start of period",
        converter = InstantConverter.class
    )
    private Instant searchPeriodTo;

    @Parameter(
        names = {"--format"},
        description = "markdown | adoc"
    )
    private String format;

}

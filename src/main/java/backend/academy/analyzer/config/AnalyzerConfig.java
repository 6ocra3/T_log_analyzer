package backend.academy.analyzer.config;

import backend.academy.analyzer.converters.LocalDateTimeConverter;
import com.beust.jcommander.Parameter;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
        converter = LocalDateTimeConverter.class
    )
    private LocalDateTime searchPeriodFrom;

    @Parameter(
        names = {"--to"},
        description = "Start of period",
        converter = LocalDateTimeConverter.class
    )
    private LocalDateTime searchPeriodTo;

    @Parameter(
        names = {"--format"},
        description = "markdown | adoc"
    )
    private String format = "adoc";

    @Parameter(
        names = {"--filter-value"},
        description = "value to filter logs"
    )
    private String filterValue;

}

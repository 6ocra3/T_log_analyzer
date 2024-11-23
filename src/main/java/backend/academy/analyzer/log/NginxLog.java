package backend.academy.analyzer.log;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@SuppressWarnings("MagicNumber")
public class NginxLog {
    public static final DateTimeFormatter DATE_FORMATTER =
        DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.US);

    private static final Pattern LOG_PATTERN = Pattern.compile(
        "^(\\S+) - - \\[(.+?)] \"(\\S+) (.+?) (\\S+)\" (\\d{3}) (\\d+) \"(.*?)\" \"(.*?)\"$"
    );

    private final String ipAddress;
    private final LocalDateTime dateTime;
    private final HttpMethod httpMethod;
    private final String requestPath;
    private final String httpVersion;
    private final HttpStatus statusCode;
    private final int responseSize;
    private final String referrer;
    private final String userAgent;

    public NginxLog(String logLine) {
        Matcher matcher = LOG_PATTERN.matcher(logLine);

        if (matcher.find()) {
            this.ipAddress = matcher.group(1);

            this.dateTime = OffsetDateTime.parse(matcher.group(2), DATE_FORMATTER).toLocalDateTime();

            this.httpMethod = HttpMethod.fromString(matcher.group(3));
            this.requestPath = matcher.group(4);
            this.httpVersion = matcher.group(5);
            this.statusCode = HttpStatus.resolve(Integer.parseInt(matcher.group(6)));
            this.responseSize = Integer.parseInt(matcher.group(7));
            this.referrer = matcher.group(8);
            this.userAgent = matcher.group(9);
        } else {
            throw new IllegalArgumentException("Неверный формат строки лога");
        }
    }
}

package backend.academy.samples;

import backend.academy.analyzer.log.HttpMethod;
import backend.academy.analyzer.log.HttpResponseCode;
import backend.academy.analyzer.log.NginxLog;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NginxLogTest {

    @Test
    public void TestLogParser() {
        // Arrange
        String example =
            "65.23.86.144 - - [27/Oct/2024:16:08:06 +0000] \"GET /Ameliorated_complexity_Synergistic.css HTTP/1.1\" 200 2098 \"-\" \"Opera/10.44 (X11; Linux x86_64; en-US) Presto/2.12.213 Version/12.00\"";
        LocalDateTime testDateTime =
            OffsetDateTime.parse("27/Oct/2024:16:08:06 +0000", NginxLog.DATE_FORMATTER).toLocalDateTime();

        // Act
        NginxLog log = new NginxLog(example);

        // Assert
        assertEquals("65.23.86.144", log.ipAddress());
        assertEquals(testDateTime, log.dateTime());
        assertEquals(HttpMethod.GET, log.httpMethod());
        assertEquals("/Ameliorated_complexity_Synergistic.css", log.requestPath());
        assertEquals("HTTP/1.1", log.httpVersion());
        assertEquals(HttpResponseCode.fromInt(200), log.statusCode());
        assertEquals(2098, log.responseSize());
        assertEquals("-", log.referrer());
        assertEquals("Opera/10.44 (X11; Linux x86_64; en-US) Presto/2.12.213 Version/12.00", log.userAgent());
    }
}

package backend.academy.analyzer.converters;

import com.beust.jcommander.IStringConverter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter implements IStringConverter<LocalDateTime> {
    private static final int DATE_LENGTH = 10;

    @Override
    public LocalDateTime convert(String s) {
        if (s.length() == DATE_LENGTH) {
            return LocalDate.parse(s).atStartOfDay();
        }
        return LocalDateTime.parse(s, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}

package backend.academy.analyzer.converters;

import com.beust.jcommander.IStringConverter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter implements IStringConverter<LocalDateTime>{
    @Override
    public LocalDateTime convert(String s) {
        if (s.length() == 10) {
            return LocalDate.parse(s).atStartOfDay();
        }
        return LocalDateTime.parse(s, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}

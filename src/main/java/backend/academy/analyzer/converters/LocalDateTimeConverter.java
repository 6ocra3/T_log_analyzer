package backend.academy.analyzer.converters;

import com.beust.jcommander.IStringConverter;
import java.time.LocalDateTime;

public class LocalDateTimeConverter implements IStringConverter<LocalDateTime>{
    @Override
    public LocalDateTime convert(String s) {
        return LocalDateTime.parse(s);
    }
}

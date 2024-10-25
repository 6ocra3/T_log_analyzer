package backend.academy.analyzer.converters;

import com.beust.jcommander.IStringConverter;
import java.time.Instant;

public class InstantConverter implements IStringConverter<Instant> {
    @Override
    public Instant convert(String s) {
        return Instant.parse(s);
    }
}

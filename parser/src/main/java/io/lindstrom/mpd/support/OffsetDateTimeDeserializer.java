package io.lindstrom.mpd.support;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

public class OffsetDateTimeDeserializer extends JsonDeserializer<OffsetDateTime> {
    private final DateTimeFormatter formatter =
            new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd'T'HH:mm:ss[XXXXX]")
                    .parseDefaulting(ChronoField.OFFSET_SECONDS, 0)
                    .toFormatter();

    @Override
    public OffsetDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String text = p.getText();

        try {
            return OffsetDateTime.parse(text);
        } catch (DateTimeParseException e) {
            // ignore
        }

        try {
            return OffsetDateTime.parse(text, formatter);
        } catch (DateTimeParseException e) {
            // ignore
        }

        ctxt.reportWrongTokenException(this, p.currentToken(), "Invalid date time");
        return null;
    }
}

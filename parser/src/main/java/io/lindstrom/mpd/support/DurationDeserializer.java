package io.lindstrom.mpd.support;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.io.IOException;
import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DurationDeserializer extends JsonDeserializer<Duration> {
    @Override
    public Duration deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String text = p.getText();

        try {
            return Duration.parse(text);
        } catch (DateTimeParseException e) {
            // ignore
        }

        // Fallback to javax.xml.datatype
        try {
            return Duration.ofMillis(DatatypeFactory.newInstance().newDuration(text).getTimeInMillis(new Date(0L)));
        } catch (DatatypeConfigurationException e) {
            throw new IOException(e);
        } catch (Exception e) {
            ctxt.reportWrongTokenException(this, p.currentToken(), "Invalid duration");
        }

        return null;
    }
}

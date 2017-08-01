package io.lindstrom.mpd.support;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.lindstrom.mpd.data.FrameRate;

import java.io.IOException;

public class FrameRateSerializer extends JsonSerializer<FrameRate> {
    @Override
    public void serialize(FrameRate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.getNumerator() +
                (value.getDenominator() == null ? "" : ("/" + value.getDenominator())));
    }
}

package io.lindstrom.mpd.support;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.lindstrom.mpd.data.Ratio;

import java.io.IOException;

public class RatioSerializer extends JsonSerializer<Ratio> {
    @Override
    public void serialize(Ratio value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        StringBuilder stringBuilder = new StringBuilder();
        if (value.getA() != null) {
            stringBuilder.append(value.getA());
        }
        stringBuilder.append(":");
        if (value.getB() != null) {
            stringBuilder.append(value.getB());
        }
        gen.writeString(stringBuilder.toString());
    }
}

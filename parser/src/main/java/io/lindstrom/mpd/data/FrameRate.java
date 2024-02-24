package io.lindstrom.mpd.data;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@JsonSerialize(using = FrameRate.Serializer.class)
@JsonDeserialize(using = FrameRate.Deserializer.class)
@Value.Immutable
public interface FrameRate {
    long numerator();

    Long denominator();

    static FrameRate of(int numerator, long denominator) {
        return FrameRate.builder()
                .numerator(numerator)
                .denominator(denominator)
                .build();
    }

    static FrameRate of(int numerator) {
        return FrameRate.builder()
                .numerator(numerator)
                .build();
    }

    default Builder buildUpon() {
        return builder().from(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableFrameRate.Builder {}

    class Serializer extends JsonSerializer<FrameRate> {
        @Override
        public void serialize(FrameRate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(value.numerator() +
                    (value.denominator() == null ? "" : ("/" + value.denominator())));
        }
    }

    class Deserializer extends JsonDeserializer<FrameRate> {
        private static final Pattern FRAME_RATE_PATTERN = Pattern.compile("^([0-9]+)(/[0-9]+)?$");

        @Override
        public FrameRate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            String text = p.getText();
            Matcher matcher = FRAME_RATE_PATTERN.matcher(text);
            if (matcher.matches()) {
                String a = matcher.group(1);
                String b = matcher.group(2);
                Builder builder = builder();
                builder.numerator(Long.parseLong(a));
                if (b != null) {
                    builder.denominator(Long.parseLong(b.substring(1)));
                }
                return builder.build();
            } else {
                ctxt.reportWrongTokenException(this, p.currentToken(), "Invalid ratio");
                return null;
            }
        }
    }
}
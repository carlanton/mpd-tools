package io.lindstrom.mpd.data;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@JsonSerialize(using = Ratio.Serializer.class)
@JsonDeserialize(using = Ratio.Deserializer.class)
public record Ratio(Long a, Long b) {
    public Builder buildUpon() {
        return builder().a(a).b(b);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Ratio of(long a, long b) {
        return new Ratio(a, b);
    }

    public static class Builder {
        private Long a;
        private Long b;

        public Builder a(Long a) {
            this.a = a;
            return this;
        }

        public Builder b(Long b) {
            this.b = b;
            return this;
        }

        public Ratio build() {
            return new Ratio(a, b);
        }
    }

    static class Serializer extends JsonSerializer<Ratio> {
        @Override
        public void serialize(Ratio value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
            StringBuilder stringBuilder = new StringBuilder();
            if (value.a() != null) {
                stringBuilder.append(value.a());
            }
            stringBuilder.append(':');
            if (value.b() != null) {
                stringBuilder.append(value.b());
            }
            gen.writeString(stringBuilder.toString());
        }
    }

    static class Deserializer extends JsonDeserializer<Ratio> {
        private static final Pattern RATIO_PATTERN = Pattern.compile("^([0-9]*):([0-9]*)$");

        @Override
        public Ratio deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            String text = p.getText();
            Matcher matcher = RATIO_PATTERN.matcher(text);
            if (matcher.matches()) {
                String a = matcher.group(1);
                String b = matcher.group(2);
                return new Ratio(a.isEmpty() ? null : Long.parseLong(a),
                        b.isEmpty() ? null : Long.parseLong(b));
            } else {
                ctxt.reportWrongTokenException(this, p.currentToken(), "Invalid ratio");
                return null;
            }
        }
    }
}
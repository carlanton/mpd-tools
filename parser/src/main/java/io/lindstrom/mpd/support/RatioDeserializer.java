package io.lindstrom.mpd.support;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.lindstrom.mpd.data.Ratio;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RatioDeserializer extends JsonDeserializer<Ratio> {
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

package io.lindstrom.mpd.support;

import com.fasterxml.jackson.databind.module.SimpleModule;
import io.lindstrom.mpd.data.FrameRate;
import io.lindstrom.mpd.data.Profiles;
import io.lindstrom.mpd.data.Ratio;

import java.time.Duration;
import java.time.OffsetDateTime;

public class ParserModule extends SimpleModule {
    public ParserModule() {
        addDeserializer(Duration.class, new DurationDeserializer());
        addSerializer(Duration.class, new DurationSerializer());

        addDeserializer(OffsetDateTime.class, new OffsetDateTimeDeserializer());
        addSerializer(OffsetDateTime.class, new OffsetDateTimeSerializer());

        addDeserializer(Ratio.class, new RatioDeserializer());
        addSerializer(Ratio.class, new RatioSerializer());

        addDeserializer(FrameRate.class, new FrameRateDeserializer());
        addSerializer(FrameRate.class, new FrameRateSerializer());

        addDeserializer(Profiles.class, new ProfilesDeserializer());
        addSerializer(Profiles.class, new ProfilesSerializer());
    }
}

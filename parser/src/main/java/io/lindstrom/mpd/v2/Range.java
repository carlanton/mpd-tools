package io.lindstrom.mpd.v2;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.immutables.value.Value;

import java.time.Duration;

@Value.Immutable
@JsonSerialize(as = ImmutableRange.class)
@JsonDeserialize(as = ImmutableRange.class)
public interface Range {
    @JacksonXmlProperty(isAttribute = true)
    Duration starttime();

    @JacksonXmlProperty(isAttribute = true)
    Duration duration();


    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableRange.Builder {}
}
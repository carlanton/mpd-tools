package io.lindstrom.mpd.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableEvent.class)
@JsonDeserialize(as = ImmutableEvent.class)
public interface Event {
    @JacksonXmlProperty(isAttribute = true)
    Long presentationTime();

    @JacksonXmlProperty(isAttribute = true)
    Long duration();

    @JacksonXmlProperty(isAttribute = true)
    Long id();

    @JacksonXmlProperty(isAttribute = true)
    String messageData();

    default Builder buildUpon() {
        return builder().from(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableEvent.Builder {}
}
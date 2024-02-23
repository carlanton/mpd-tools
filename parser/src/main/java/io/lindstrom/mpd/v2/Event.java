package io.lindstrom.mpd.v2;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.immutables.value.Value;

@Value.Immutable
public interface Event {
    @JacksonXmlProperty(isAttribute = true)
    Long presentationTime();

    @JacksonXmlProperty(isAttribute = true)
    Long duration();

    @JacksonXmlProperty(isAttribute = true)
    Long id();

    @JacksonXmlProperty(isAttribute = true)
    String messageData();

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableEvent.Builder {}
}
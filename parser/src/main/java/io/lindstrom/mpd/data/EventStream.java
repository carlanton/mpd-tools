package io.lindstrom.mpd.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.immutables.value.Value;


import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableEventStream.class)
@JsonDeserialize(as = ImmutableEventStream.class)
public interface EventStream {
    @JacksonXmlProperty(isAttribute = true)
    String schemeIdUri();

    @JacksonXmlProperty(isAttribute = true)
    String value();

    @JacksonXmlProperty(isAttribute = true)
    Long timescale();

    @JacksonXmlProperty(isAttribute = true)
    String messageData();

    @JacksonXmlProperty(localName = "Event", namespace = MPD.NAMESPACE)
    List<Event> events();

    @JacksonXmlProperty(isAttribute = true, namespace = "http://www.w3.org/1999/xlink")
    String href();

    @JacksonXmlProperty(isAttribute = true, namespace = "http://www.w3.org/1999/xlink")
    ActuateType actuate();

    default Builder buildUpon() {
        return builder().from(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableEventStream.Builder {}
}
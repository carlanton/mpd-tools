package io.lindstrom.mpd.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableSegmentURL.class)
@JsonDeserialize(as = ImmutableSegmentURL.class)
public interface SegmentURL {
    @JacksonXmlProperty(isAttribute = true)
    String media();

    @JacksonXmlProperty(isAttribute = true)
    String mediaRange();

    @JacksonXmlProperty(isAttribute = true)
    String index();

    @JacksonXmlProperty(isAttribute = true)
    String indexRange();

    default Builder buildUpon() {
        return builder().from(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableSegmentURL.Builder {}
}
package io.lindstrom.mpd.v2;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableSegmentBase.class)
@JsonDeserialize(as = ImmutableSegmentBase.class)
public interface SegmentBase {
    @JacksonXmlProperty(isAttribute = true)
    Long timescale();

    @JacksonXmlProperty(isAttribute = true)
    Long presentationTimeOffset();

    @JacksonXmlProperty(isAttribute = true)
    String indexRange();

    @JacksonXmlProperty(isAttribute = true)
    Boolean indexRangeExact();

    @JacksonXmlProperty(isAttribute = true)
    Double availabilityTimeOffset();

    @JacksonXmlProperty(isAttribute = true)
    Boolean availabilityTimeComplete();

    @JacksonXmlProperty(localName = "Initialization", namespace = MPD.NAMESPACE)
    URLType initialization();

    @JacksonXmlProperty(localName = "RepresentationIndex", namespace = MPD.NAMESPACE)
    URLType representationIndex();

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableSegmentBase.Builder {}
}
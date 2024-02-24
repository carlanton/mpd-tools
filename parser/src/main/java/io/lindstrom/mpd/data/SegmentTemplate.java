package io.lindstrom.mpd.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableSegmentTemplate.class)
@JsonDeserialize(as = ImmutableSegmentTemplate.class)
@JsonPropertyOrder({
        "initialization",
        "media",
        "startNumber",
        "timescale",
        "duration",

        "Initialization",
        "RepresentationIndex",
        "segmentTimeline",
        "BitstreamSwitching"
})
public interface SegmentTemplate {
    @JacksonXmlProperty(isAttribute = true)
    String media();

    @JacksonXmlProperty(isAttribute = true)
    String index();

    @JacksonXmlProperty(isAttribute = true)
    String initialization();

    @JacksonXmlProperty(isAttribute = true)
    String bitstreamSwitching();

    @JacksonXmlProperty(isAttribute = true)
    Long duration();

    @JacksonXmlProperty(isAttribute = true)
    Long startNumber();

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

    @JacksonXmlProperty(localName = "S", namespace = MPD.NAMESPACE)
    @JacksonXmlElementWrapper(localName = "SegmentTimeline", namespace = MPD.NAMESPACE)
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    List<Segment> segmentTimeline();

    @JacksonXmlProperty(localName = "BitstreamSwitching", namespace = MPD.NAMESPACE)
    URLType bitstreamswitchingElement();

    @JacksonXmlProperty(localName = "Initialization", namespace = MPD.NAMESPACE)
    URLType initializationElement();

    @JacksonXmlProperty(localName = "RepresentationIndex", namespace = MPD.NAMESPACE)
    URLType representationIndex();

    default Builder buildUpon() {
        return builder().from(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableSegmentTemplate.Builder {}
}
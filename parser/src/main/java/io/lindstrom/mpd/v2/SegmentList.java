package io.lindstrom.mpd.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableSegmentList.class)
@JsonDeserialize(as = ImmutableSegmentList.class)
public interface SegmentList {
    @JacksonXmlProperty(isAttribute = true)
    Long duration();

    @JacksonXmlProperty(isAttribute = true)
    Long startNumber();

    @JacksonXmlProperty(isAttribute = true, namespace = "http://www.w3.org/1999/xlink")
    String href();

    @JacksonXmlProperty(isAttribute = true, namespace = "http://www.w3.org/1999/xlink")
    ActuateType actuate();

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

    @JacksonXmlProperty(localName = "S", namespace = MPD.NAMESPACE)
    @JacksonXmlElementWrapper(localName = "SegmentTimeline", namespace = MPD.NAMESPACE)
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    List<Segment> segmentTimeline();

    @JacksonXmlProperty(localName = "BitstreamSwitching", namespace = MPD.NAMESPACE)
    URLType bitstreamswitchingElement();

    @JacksonXmlProperty(localName = "SegmentURL", namespace = MPD.NAMESPACE)
    List<SegmentURL> segmentURLs();
    
    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableSegmentList.Builder {}
}
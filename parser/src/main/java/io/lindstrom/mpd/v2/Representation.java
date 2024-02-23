package io.lindstrom.mpd.v2;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableRepresentation.class)
@JsonDeserialize(as = ImmutableRepresentation.class)
public interface Representation extends RepresentationBase {
    @JacksonXmlProperty(isAttribute = true)
    String id();

    @JacksonXmlProperty(isAttribute = true)
    long bandwidth();

    @JacksonXmlProperty(isAttribute = true)
    Long qualityRanking();

    @JacksonXmlProperty(isAttribute = true)
    String dependencyId();

    @JacksonXmlProperty(isAttribute = true)
    String mediaStreamStructureId();

    @JacksonXmlProperty(localName = "BaseURL", namespace = MPD.NAMESPACE)
    List<BaseURL> baseURLs();

    @JacksonXmlProperty(localName = "SubRepresentation", namespace = MPD.NAMESPACE)
    List<SubRepresentation> subRepresentations();

    @JacksonXmlProperty(localName = "SegmentBase", namespace = MPD.NAMESPACE)
    SegmentBase segmentBase();

    @JacksonXmlProperty(localName = "SegmentList", namespace = MPD.NAMESPACE)
    SegmentList segmentList();

    @JacksonXmlProperty(localName = "SegmentTemplate", namespace = MPD.NAMESPACE)
    SegmentTemplate segmentTemplate();
    
    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableRepresentation.Builder {}
}
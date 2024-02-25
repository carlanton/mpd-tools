package io.lindstrom.mpd.data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.lindstrom.mpd.data.descriptor.Descriptor;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableAdaptationSet.class)
@JsonDeserialize(as = ImmutableAdaptationSet.class)
@JsonPropertyOrder({
        "id",
        "contentType",
        "mimeType",
        "segmentAlignment",

        "framePackings",
        "audioChannelConfigurations",
        "contentProtections",
        "essentialProperties",
        "supplementalProperties",
        "inbandEventStreams",
        "accessibilities",
        "roles",
        "ratings",
        "viewpoints",
        "contentComponents",
        "baseURLs",
        "segmentBase",
        "segmentList",
        "segmentTemplate",
        "labels",
        "representations"
})
public interface AdaptationSet extends RepresentationBase {
    @JacksonXmlProperty(isAttribute = true, namespace = "http://www.w3.org/1999/xlink")
    String href();

    @JacksonXmlProperty(isAttribute = true,  namespace = "http://www.w3.org/1999/xlink")
    ActuateType actuate();

    @JacksonXmlProperty(isAttribute = true)
    Long id();

    @JacksonXmlProperty(isAttribute = true)
    Long group();

    @JacksonXmlProperty(isAttribute = true)
    String lang();

    @JacksonXmlProperty(isAttribute = true)
    String contentType();

    @JacksonXmlProperty(isAttribute = true)
    Ratio par();

    @JacksonXmlProperty(isAttribute = true)
    Long minBandwidth();

    @JacksonXmlProperty(isAttribute = true)
    Long maxBandwidth();

    @JacksonXmlProperty(isAttribute = true)
    Long minWidth();

    @JacksonXmlProperty(isAttribute = true)
    Long maxWidth();

    @JacksonXmlProperty(isAttribute = true)
    Long minHeight();

    @JacksonXmlProperty(isAttribute = true)
    Long maxHeight();

    @JacksonXmlProperty(isAttribute = true)
    FrameRate minFrameRate();

    @JacksonXmlProperty(isAttribute = true)
    FrameRate maxFrameRate();

    @JacksonXmlProperty(isAttribute = true)
    String segmentAlignment();

    @JacksonXmlProperty(isAttribute = true)
    String subsegmentAlignment();

    @JacksonXmlProperty(isAttribute = true)
    Long subsegmentStartsWithSAP();

    @JacksonXmlProperty(isAttribute = true)
    Boolean bitstreamSwitching();

    @JacksonXmlProperty(localName = "Accessibility", namespace = MPD.NAMESPACE)
    List<Descriptor> accessibilities();

    @JacksonXmlProperty(localName = "Role", namespace = MPD.NAMESPACE)
    List<Descriptor> roles();

    @JacksonXmlProperty(localName = "Rating", namespace = MPD.NAMESPACE)
    List<Descriptor> ratings();

    @JacksonXmlProperty(localName = "Viewpoint", namespace = MPD.NAMESPACE)
    List<Descriptor> viewpoints();

    @JacksonXmlProperty(localName = "ContentComponent", namespace = MPD.NAMESPACE)
    List<ContentComponent> contentComponents();

    @JacksonXmlProperty(localName = "BaseURL", namespace = MPD.NAMESPACE)
    List<BaseURL> baseURLs();

    @JacksonXmlProperty(localName = "SegmentBase", namespace = MPD.NAMESPACE)
    SegmentBase segmentBase();

    @JacksonXmlProperty(localName = "SegmentList", namespace = MPD.NAMESPACE)
    SegmentList segmentList();

    @JacksonXmlProperty(localName = "SegmentTemplate", namespace = MPD.NAMESPACE)
    SegmentTemplate segmentTemplate();

    @JacksonXmlProperty(localName = "Representation", namespace = MPD.NAMESPACE)
    List<Representation> representations();

    default Builder buildUpon() {
        return builder().from(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableAdaptationSet.Builder {}
}
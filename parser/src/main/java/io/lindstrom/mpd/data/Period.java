package io.lindstrom.mpd.data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.lindstrom.mpd.data.descriptor.Descriptor;
import org.immutables.value.Value;

import java.time.Duration;
import java.util.List;

@Value.Immutable
@JacksonXmlRootElement(localName = "Period", namespace = MPD.NAMESPACE)
@JsonSerialize(as = ImmutablePeriod.class)
@JsonDeserialize(as = ImmutablePeriod.class)
@JsonPropertyOrder({
        "id",

        "baseURLs",
        "segmentBase",
        "segmentList",
        "segmentTemplate",
        "assetIdentifier",
        "eventStreams",
        "adaptationSets",
        "subsets",
        "supplementalProperties"
})
public interface Period {
    @JacksonXmlProperty(isAttribute = true, namespace = "http://www.w3.org/1999/xlink")
    String href();

    @JacksonXmlProperty(isAttribute = true, namespace = "http://www.w3.org/1999/xlink")
    ActuateType actuate();

    @JacksonXmlProperty(isAttribute = true)
    String id();

    @JacksonXmlProperty(isAttribute = true)
    Duration start();

    @JacksonXmlProperty(isAttribute = true)
    Duration duration();

    @JacksonXmlProperty(isAttribute = true)
    Boolean bitstreamSwitching();

    @JacksonXmlProperty(localName = "BaseURL", namespace = MPD.NAMESPACE)
    List<BaseURL> baseURLs();

    @JacksonXmlProperty(localName = "SegmentBase", namespace = MPD.NAMESPACE)
    SegmentBase segmentBase();

    @JacksonXmlProperty(localName = "SegmentList", namespace = MPD.NAMESPACE)
    SegmentList segmentList();

    @JacksonXmlProperty(localName = "SegmentTemplate", namespace = MPD.NAMESPACE)
    SegmentTemplate segmentTemplate();

    @JacksonXmlProperty(localName = "AssetIdentifier", namespace = MPD.NAMESPACE)
    Descriptor assetIdentifier();

    @JacksonXmlProperty(localName = "EventStream", namespace = MPD.NAMESPACE)
    List<EventStream> eventStreams();

    @JacksonXmlProperty(localName = "AdaptationSet", namespace = MPD.NAMESPACE)
    List<AdaptationSet> adaptationSets();

    @JacksonXmlProperty(localName = "Subset", namespace = MPD.NAMESPACE)
    List<Subset> subsets();

    @JacksonXmlProperty(localName = "SupplementalProperty", namespace = MPD.NAMESPACE)
    List<Descriptor> supplementalProperties();

    default Builder buildUpon() {
        return builder().from(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutablePeriod.Builder {}
}
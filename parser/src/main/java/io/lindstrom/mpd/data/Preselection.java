package io.lindstrom.mpd.data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.lindstrom.mpd.data.descriptor.Descriptor;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutablePreselection.class)
@JsonDeserialize(as = ImmutablePreselection.class)
@JsonPropertyOrder({
        "framePackings", "audioChannelConfigurations", "contentProtections",
        "essentialProperties", "supplementalProperties", "inbandEventStreams",
        "labels",

        "id", "preselectionComponents", "lang", "order", // attributes
        "accessibilities", "roles", "ratings", "viewpoints" // elements
})
public interface Preselection extends RepresentationBase {
    @JacksonXmlProperty(localName = "Accessibility", namespace = MPD.NAMESPACE)
    List<Descriptor> accessibilities();

    @JacksonXmlProperty(localName = "Role", namespace = MPD.NAMESPACE)
    List<Descriptor> roles();

    @JacksonXmlProperty(localName = "Rating", namespace = MPD.NAMESPACE)
    List<Descriptor> ratings();

    @JacksonXmlProperty(localName = "Viewpoint", namespace = MPD.NAMESPACE)
    List<Descriptor> viewpoints();

    @JacksonXmlProperty(isAttribute = true)
    String id();

    @JacksonXmlProperty(isAttribute = true)
    String preselectionComponents();

    @JacksonXmlProperty(isAttribute = true)
    String lang();

    @JacksonXmlProperty(isAttribute = true)
    PreselectionOrder order();

    default Builder buildUpon() {
        return builder().from(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutablePreselection.Builder {}
}
package io.lindstrom.mpd.v2;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.lindstrom.mpd.v2.descriptor.Descriptor;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableContentComponent.class)
@JsonDeserialize(as = ImmutableContentComponent.class)
public interface ContentComponent {
    @JacksonXmlProperty(localName = "Accessibility", namespace = MPD.NAMESPACE)
    List<Descriptor> accessibilities();

    @JacksonXmlProperty(localName = "Role", namespace = MPD.NAMESPACE)
    List<Descriptor> roles();

    @JacksonXmlProperty(localName = "Rating", namespace = MPD.NAMESPACE)
    List<Descriptor> ratings();

    @JacksonXmlProperty(localName = "Viewpoint", namespace = MPD.NAMESPACE)
    List<Descriptor> viewpoints();

    @JacksonXmlProperty(isAttribute = true)
    Long id();

    @JacksonXmlProperty(isAttribute = true)
    String lang();

    @JacksonXmlProperty(isAttribute = true)
    String contentType();

    @JacksonXmlProperty(isAttribute = true)
    Ratio par();

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableContentComponent.Builder {}
}
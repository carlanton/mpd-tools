package io.lindstrom.mpd.v2.descriptor.protection;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.lindstrom.mpd.v2.descriptor.Descriptor;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableMp4Protection.class)
@JsonDeserialize(as = ImmutableMp4Protection.class)
public interface Mp4Protection extends Descriptor {
    String SCHEME_ID_URI = "urn:mpeg:dash:mp4protection:2011";

    @JacksonXmlProperty(isAttribute = true, localName = "default_KID", namespace = "urn:mpeg:cenc:2013")
    String defaultKID();

    @JacksonXmlProperty(isAttribute = true, localName = "value")
    String value();

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableMp4Protection.Builder {}
}
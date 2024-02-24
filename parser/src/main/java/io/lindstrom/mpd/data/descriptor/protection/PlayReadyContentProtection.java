package io.lindstrom.mpd.data.descriptor.protection;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.lindstrom.mpd.data.descriptor.Descriptor;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutablePlayReadyContentProtection.class)
@JsonDeserialize(as = ImmutablePlayReadyContentProtection.class)
public interface PlayReadyContentProtection extends Descriptor {
    String SCHEME_ID_URI = "urn:uuid:9a04f079-9840-4286-ab92-e65be0885f95";

    @JacksonXmlProperty(isAttribute = true, localName = "value")
    String value();

    @JacksonXmlProperty(isAttribute = true, localName = "default_KID", namespace = "urn:mpeg:cenc:2013")
    String defaultKID();

    @JacksonXmlProperty(namespace = "urn:mpeg:cenc:2013")
    String pssh();

    @JacksonXmlProperty(namespace = "urn:microsoft:playready")
    String pro();

    default Builder buildUpon() {
        return builder().from(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutablePlayReadyContentProtection.Builder {}
}
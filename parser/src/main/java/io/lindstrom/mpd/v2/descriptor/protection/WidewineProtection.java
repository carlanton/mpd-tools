package io.lindstrom.mpd.v2.descriptor.protection;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.lindstrom.mpd.v2.descriptor.Descriptor;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableWidewineProtection.class)
@JsonDeserialize(as = ImmutableWidewineProtection.class)
public interface WidewineProtection extends Descriptor {
    String SCHEME_ID_URI = "urn:uuid:edef8ba9-79d6-4ace-a3c8-27dcd51d21ed";

    @JacksonXmlProperty(isAttribute = true, localName = "value")
    String value();

    @JacksonXmlProperty(namespace = "urn:mpeg:cenc:2013")
    String pssh();

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableWidewineProtection.Builder {}
}
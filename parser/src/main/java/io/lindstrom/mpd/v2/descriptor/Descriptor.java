package io.lindstrom.mpd.v2.descriptor;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.lindstrom.mpd.v2.descriptor.protection.Mp4Protection;
import io.lindstrom.mpd.v2.descriptor.protection.PlayReadyContentProtection;
import io.lindstrom.mpd.v2.descriptor.protection.WidewineProtection;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "schemeIdUri",
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        visible = true,
        defaultImpl = GenericDescriptor.class)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Role.class, name = Role.SCHEME_ID_URI),
        @JsonSubTypes.Type(value = Mp4Protection.class, name = Mp4Protection.SCHEME_ID_URI),
        @JsonSubTypes.Type(value = PlayReadyContentProtection.class, name = PlayReadyContentProtection.SCHEME_ID_URI),
        @JsonSubTypes.Type(value = WidewineProtection.class, name = WidewineProtection.SCHEME_ID_URI)
})
@JsonPropertyOrder({
        "schemeIdUri", "id"
})
public interface Descriptor {
    @JacksonXmlProperty(isAttribute = true)
    String schemeIdUri();

    @JacksonXmlProperty(isAttribute = true)
    String id();
}
package io.lindstrom.mpd.v2.descriptor;


import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.immutables.value.Value;

import java.util.Locale;
import java.util.Objects;

@Value.Immutable
@JsonSerialize(as = ImmutableRole.class)
@JsonDeserialize(as = ImmutableRole.class)
public interface Role extends Descriptor {
    String SCHEME_ID_URI = "urn:mpeg:dash:role:2011";

    @Override
    @Value.Default
    default String schemeIdUri() {
        return SCHEME_ID_URI;
    }

    @JacksonXmlProperty(localName = "value", isAttribute = true)
    Type type();

    enum Type {
        @JsonProperty("caption") CAPTION,
        @JsonProperty("subtitle") SUBTITLE,
        @JsonProperty("main") MAIN,
        @JsonProperty("alternate") ALTERNATE,
        @JsonProperty("supplementary") SUPPLEMENTARY,
        @JsonProperty("commentary") COMMENTARY,
        @JsonProperty("dub") DUB,
        @JsonProperty("description") DESCRIPTION,
        @JsonProperty("enhanced-audio-intelligibility") ENHANCED_AUDIO_INTELLIGIBILITY,
        @JsonProperty("emergency") EMERGENCY,
        @JsonProperty("sign") SIGN,

        @JsonEnumDefaultValue UNKNOWN
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableRole.Builder {}
}
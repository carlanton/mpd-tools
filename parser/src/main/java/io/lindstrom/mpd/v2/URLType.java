package io.lindstrom.mpd.v2;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableURLType.class)
@JsonDeserialize(as = ImmutableURLType.class)
public interface URLType {
    @JacksonXmlProperty(isAttribute = true)
    String sourceURL();

    @JacksonXmlProperty(isAttribute = true)
    String range();

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableURLType.Builder {}
}
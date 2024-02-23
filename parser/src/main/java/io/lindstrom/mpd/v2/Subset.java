package io.lindstrom.mpd.v2;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableSubset.class)
@JsonDeserialize(as = ImmutableSubset.class)
public interface Subset {
    @JacksonXmlProperty(isAttribute = true)
    String contains();

    @JacksonXmlProperty(isAttribute = true)
    String id();

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableSubset.Builder {}
}
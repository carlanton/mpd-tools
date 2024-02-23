package io.lindstrom.mpd.v2.descriptor;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableGenericDescriptor.class)
@JsonDeserialize(as = ImmutableGenericDescriptor.class)
public interface GenericDescriptor extends Descriptor {
    @JacksonXmlProperty(isAttribute = true, localName = "value")
    String value();

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableGenericDescriptor.Builder {}
}
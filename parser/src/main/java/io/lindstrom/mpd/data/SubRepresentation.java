package io.lindstrom.mpd.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableSubRepresentation.class)
@JsonDeserialize(as = ImmutableSubRepresentation.class)
public interface SubRepresentation {
    @JacksonXmlProperty(isAttribute = true)
    Long level();

    @JacksonXmlProperty(isAttribute = true)
    String dependencyLevel();

    @JacksonXmlProperty(isAttribute = true)
    Long bandwidth();

    @JacksonXmlProperty(isAttribute = true)
    String contentComponent();

    default Builder buildUpon() {
        return builder().from(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableSubRepresentation.Builder {}
}
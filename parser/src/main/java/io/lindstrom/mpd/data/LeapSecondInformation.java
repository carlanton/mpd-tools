package io.lindstrom.mpd.data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.immutables.value.Value;

import java.time.OffsetDateTime;

@Value.Immutable
@JsonSerialize(as = ImmutableLeapSecondInformation.class)
@JsonDeserialize(as = ImmutableLeapSecondInformation.class)
@JsonPropertyOrder({"availabilityStartLeapOffset", "nextAvailabilityStartLeapOffset", "nextLeapChangeTime"})
public interface LeapSecondInformation {
    @JacksonXmlProperty(isAttribute = true)
    int availabilityStartLeapOffset();

    @JacksonXmlProperty(isAttribute = true)
    Integer nextAvailabilityStartLeapOffset();

    @JacksonXmlProperty(isAttribute = true)
    OffsetDateTime nextLeapChangeTime();


    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableLeapSecondInformation.Builder {}

    default Builder buildUpon() {
        return builder().from(this);
    }
}
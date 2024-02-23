package io.lindstrom.mpd.v2;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.lindstrom.mpd.v2.descriptor.Descriptor;
import org.immutables.value.Value;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

@Value.Immutable
@JacksonXmlRootElement(localName = "MPD", namespace = io.lindstrom.mpd.v2.MPD.NAMESPACE)
@JsonSerialize(as = ImmutableMPD.class)
@JsonDeserialize(as = ImmutableMPD.class)
public interface MPD {
    String NAMESPACE = "urn:mpeg:dash:schema:mpd:2011";
    String DEFAULT_SCHEMA_LOCATION = "urn:mpeg:dash:schema:mpd:2011 http://standards.iso.org/ittf/PubliclyAvailableStandards/MPEG-DASH_schema_files/DASH-MPD.xsd";

    @JacksonXmlProperty(isAttribute = true, namespace = "http://www.w3.org/2001/XMLSchema-instance")
    String schemaLocation();

    @JacksonXmlProperty(isAttribute = true)
    String id();

    @JacksonXmlProperty(isAttribute = true)
    Profiles profiles();

    @JacksonXmlProperty(isAttribute = true)
    PresentationType type();

    @JacksonXmlProperty(isAttribute = true)
    OffsetDateTime availabilityStartTime();

    @JacksonXmlProperty(isAttribute = true)
    OffsetDateTime availabilityEndTime();

    @JacksonXmlProperty(isAttribute = true)
    OffsetDateTime publishTime();

    @JacksonXmlProperty(isAttribute = true)
    Duration mediaPresentationDuration();

    @JacksonXmlProperty(isAttribute = true)
    Duration minimumUpdatePeriod();

    @JacksonXmlProperty(isAttribute = true)
    Duration minBufferTime();

    @JacksonXmlProperty(isAttribute = true)
    Duration timeShiftBufferDepth();

    @JacksonXmlProperty(isAttribute = true)
    Duration suggestedPresentationDelay();

    @JacksonXmlProperty(isAttribute = true)
    Duration maxSegmentDuration();

    @JacksonXmlProperty(isAttribute = true)
    Duration maxSubsegmentDuration();

    @JacksonXmlProperty(localName = "ProgramInformation", namespace = NAMESPACE)
    List<ProgramInformation> programInformations();

    @JacksonXmlProperty(localName = "BaseURL", namespace = NAMESPACE)
    List<BaseURL> baseURLs();

    @JacksonXmlProperty(localName = "Location", namespace = NAMESPACE)
    List<String> locations();

    @JacksonXmlProperty(localName = "Period", namespace = NAMESPACE)
    List<Period> periods();

    @JacksonXmlProperty(localName = "Metrics", namespace = NAMESPACE)
    List<Metrics> metrics();

    @JacksonXmlProperty(localName = "EssentialProperty", namespace = NAMESPACE)
    List<Descriptor> essentialProperties();

    @JacksonXmlProperty(localName = "SupplementalProperty", namespace = NAMESPACE)
    List<Descriptor> supplementalProperties();

    @JacksonXmlProperty(localName = "UTCTiming", namespace = NAMESPACE)
    List<UTCTiming> utcTimings();

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableMPD.Builder {}
}
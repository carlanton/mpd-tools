package io.lindstrom.mpd.data;

import io.lindstrom.mpd.support.Utils;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@XmlType(propOrder = {
    "programInformations",
    "baseURLs",
    "locations",
    "periods",
    "metrics",
    "essentialProperties",
    "supplementalProperties",
    "utcTimings"
})
@XmlRootElement(name = "MPD", namespace = io.lindstrom.mpd.data.MPD.NAMESPACE)
public class MPD {
    static final String NAMESPACE = "urn:mpeg:dash:schema:mpd:2011";

    @XmlElement(name = "ProgramInformation", namespace = NAMESPACE)
    private final List<ProgramInformation> programInformations;

    @XmlElement(name = "BaseURL", namespace = NAMESPACE)
    private final List<BaseURL> baseURLs;

    @XmlElement(name = "Location", namespace = NAMESPACE)
    private final List<String> locations;

    @XmlElement(name = "Period", required = true, namespace = NAMESPACE)
    private final List<Period> periods;

    @XmlElement(name = "Metrics", namespace = NAMESPACE)
    private final List<Metrics> metrics;

    @XmlElement(name = "EssentialProperty", namespace = NAMESPACE)
    private final List<Descriptor> essentialProperties;

    @XmlElement(name = "SupplementalProperty", namespace = NAMESPACE)
    private final List<Descriptor> supplementalProperties;

    @XmlElement(name = "UTCTiming", namespace = NAMESPACE)
    private final List<Descriptor> utcTimings;

    @XmlAttribute(name = "id")
    private final String id;

    @XmlAttribute(name = "profiles", required = true)
    private final String profiles;

    @XmlAttribute(name = "type")
    private final PresentationType type;

    @XmlAttribute(name = "availabilityStartTime")
    private final OffsetDateTime availabilityStartTime;

    @XmlAttribute(name = "availabilityEndTime")
    private final OffsetDateTime availabilityEndTime;

    @XmlAttribute(name = "publishTime")
    private final OffsetDateTime publishTime;

    @XmlAttribute(name = "mediaPresentationDuration")
    private final Duration mediaPresentationDuration;

    @XmlAttribute(name = "minimumUpdatePeriod")
    private final Duration minimumUpdatePeriod;

    @XmlAttribute(name = "minBufferTime", required = true)
    private final Duration minBufferTime;

    @XmlAttribute(name = "timeShiftBufferDepth")
    private final Duration timeShiftBufferDepth;

    @XmlAttribute(name = "suggestedPresentationDelay")
    private final Duration suggestedPresentationDelay;

    @XmlAttribute(name = "maxSegmentDuration")
    private final Duration maxSegmentDuration;

    @XmlAttribute(name = "maxSubsegmentDuration")
    private final Duration maxSubsegmentDuration;

    @XmlAttribute(namespace = "http://www.w3.org/2001/XMLSchema-instance")
    private final String schemaLocation;

    private MPD(List<ProgramInformation> programInformations, List<BaseURL> baseURLs, List<String> locations, List<Period> periods, List<Metrics> metrics, List<Descriptor> essentialProperties, List<Descriptor> supplementalProperties, List<Descriptor> utcTimings, String id, String profiles, PresentationType type, OffsetDateTime availabilityStartTime, OffsetDateTime availabilityEndTime, OffsetDateTime publishTime, Duration mediaPresentationDuration, Duration minimumUpdatePeriod, Duration minBufferTime, Duration timeShiftBufferDepth, Duration suggestedPresentationDelay, Duration maxSegmentDuration, Duration maxSubsegmentDuration, String schemaLocation) {
        this.programInformations = programInformations;
        this.baseURLs = baseURLs;
        this.locations = locations;
        this.periods = periods;
        this.metrics = metrics;
        this.essentialProperties = essentialProperties;
        this.supplementalProperties = supplementalProperties;
        this.utcTimings = utcTimings;
        this.id = id;
        this.profiles = profiles;
        this.type = type;
        this.availabilityStartTime = availabilityStartTime;
        this.availabilityEndTime = availabilityEndTime;
        this.publishTime = publishTime;
        this.mediaPresentationDuration = mediaPresentationDuration;
        this.minimumUpdatePeriod = minimumUpdatePeriod;
        this.minBufferTime = minBufferTime;
        this.timeShiftBufferDepth = timeShiftBufferDepth;
        this.suggestedPresentationDelay = suggestedPresentationDelay;
        this.maxSegmentDuration = maxSegmentDuration;
        this.maxSubsegmentDuration = maxSubsegmentDuration;
        this.schemaLocation = schemaLocation;
    }

    @SuppressWarnings("unused")
    private MPD() {
        this.programInformations = null;
        this.baseURLs = null;
        this.locations = null;
        this.periods = null;
        this.metrics = null;
        this.essentialProperties = null;
        this.supplementalProperties = null;
        this.utcTimings = null;
        this.id = null;
        this.profiles = null;
        this.type = null;
        this.availabilityStartTime = null;
        this.availabilityEndTime = null;
        this.publishTime = null;
        this.mediaPresentationDuration = null;
        this.minimumUpdatePeriod = null;
        this.minBufferTime = null;
        this.timeShiftBufferDepth = null;
        this.suggestedPresentationDelay = null;
        this.maxSegmentDuration = null;
        this.maxSubsegmentDuration = null;
        this.schemaLocation = null;
    }

    public List<ProgramInformation> getProgramInformations() {
        return Utils.unmodifiableList(programInformations);
    }

    public List<BaseURL> getBaseURLs() {
        return Utils.unmodifiableList(baseURLs);
    }

    public List<String> getLocations() {
        return Utils.unmodifiableList(locations);
    }

    public List<Period> getPeriods() {
        return Utils.unmodifiableList(periods);
    }

    public List<Metrics> getMetrics() {
        return Utils.unmodifiableList(metrics);
    }

    public List<Descriptor> getEssentialProperties() {
        return Utils.unmodifiableList(essentialProperties);
    }

    public List<Descriptor> getSupplementalProperties() {
        return Utils.unmodifiableList(supplementalProperties);
    }

    public List<Descriptor> getUtcTimings() {
        return Utils.unmodifiableList(utcTimings);
    }

    public String getId() {
        return id;
    }

    public String getProfiles() {
        return profiles;
    }

    public PresentationType getType() {
        return type;
    }

    public OffsetDateTime getAvailabilityStartTime() {
        return availabilityStartTime;
    }

    public OffsetDateTime getAvailabilityEndTime() {
        return availabilityEndTime;
    }

    public OffsetDateTime getPublishTime() {
        return publishTime;
    }

    public Duration getMediaPresentationDuration() {
        return mediaPresentationDuration;
    }

    public Duration getMinimumUpdatePeriod() {
        return minimumUpdatePeriod;
    }

    public Duration getMinBufferTime() {
        return minBufferTime;
    }

    public Duration getTimeShiftBufferDepth() {
        return timeShiftBufferDepth;
    }

    public Duration getSuggestedPresentationDelay() {
        return suggestedPresentationDelay;
    }

    public Duration getMaxSegmentDuration() {
        return maxSegmentDuration;
    }

    public Duration getMaxSubsegmentDuration() {
        return maxSubsegmentDuration;
    }

    public String getSchemaLocation() {
        return schemaLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MPD mpd = (MPD) o;
        return Objects.equals(programInformations, mpd.programInformations) &&
                Objects.equals(baseURLs, mpd.baseURLs) &&
                Objects.equals(locations, mpd.locations) &&
                Objects.equals(periods, mpd.periods) &&
                Objects.equals(metrics, mpd.metrics) &&
                Objects.equals(essentialProperties, mpd.essentialProperties) &&
                Objects.equals(supplementalProperties, mpd.supplementalProperties) &&
                Objects.equals(utcTimings, mpd.utcTimings) &&
                Objects.equals(id, mpd.id) &&
                Objects.equals(profiles, mpd.profiles) &&
                type == mpd.type &&
                Objects.equals(availabilityStartTime, mpd.availabilityStartTime) &&
                Objects.equals(availabilityEndTime, mpd.availabilityEndTime) &&
                Objects.equals(publishTime, mpd.publishTime) &&
                Objects.equals(mediaPresentationDuration, mpd.mediaPresentationDuration) &&
                Objects.equals(minimumUpdatePeriod, mpd.minimumUpdatePeriod) &&
                Objects.equals(minBufferTime, mpd.minBufferTime) &&
                Objects.equals(timeShiftBufferDepth, mpd.timeShiftBufferDepth) &&
                Objects.equals(suggestedPresentationDelay, mpd.suggestedPresentationDelay) &&
                Objects.equals(maxSegmentDuration, mpd.maxSegmentDuration) &&
                Objects.equals(maxSubsegmentDuration, mpd.maxSubsegmentDuration) &&
                Objects.equals(schemaLocation, mpd.schemaLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(programInformations, baseURLs, locations, periods, metrics, essentialProperties, supplementalProperties, utcTimings, id, profiles, type, availabilityStartTime, availabilityEndTime, publishTime, mediaPresentationDuration, minimumUpdatePeriod, minBufferTime, timeShiftBufferDepth, suggestedPresentationDelay, maxSegmentDuration, maxSubsegmentDuration, schemaLocation);
    }

    @Override
    public String toString() {
        return "MPD{" +
                "programInformations=" + programInformations +
                ", baseURLs=" + baseURLs +
                ", locations=" + locations +
                ", periods=" + periods +
                ", metrics=" + metrics +
                ", essentialProperties=" + essentialProperties +
                ", supplementalProperties=" + supplementalProperties +
                ", utcTimings=" + utcTimings +
                ", id='" + id + '\'' +
                ", profiles='" + profiles + '\'' +
                ", type=" + type +
                ", availabilityStartTime=" + availabilityStartTime +
                ", availabilityEndTime=" + availabilityEndTime +
                ", publishTime=" + publishTime +
                ", mediaPresentationDuration=" + mediaPresentationDuration +
                ", minimumUpdatePeriod=" + minimumUpdatePeriod +
                ", minBufferTime=" + minBufferTime +
                ", timeShiftBufferDepth=" + timeShiftBufferDepth +
                ", suggestedPresentationDelay=" + suggestedPresentationDelay +
                ", maxSegmentDuration=" + maxSegmentDuration +
                ", maxSubsegmentDuration=" + maxSubsegmentDuration +
                ", schemaLocation='" + schemaLocation + '\'' +
                '}';
    }

    public Builder buildUpon() {
        return new Builder()
                .withProgramInformations(programInformations)
                .withBaseURLs(baseURLs)
                .withLocations(locations)
                .withPeriods(periods)
                .withMetrics(metrics)
                .withEssentialProperties(essentialProperties)
                .withSupplementalProperties(supplementalProperties)
                .withUtcTimings(utcTimings)
                .withId(id)
                .withProfiles(profiles)
                .withType(type)
                .withAvailabilityStartTime(availabilityStartTime)
                .withAvailabilityEndTime(availabilityEndTime)
                .withPublishTime(publishTime)
                .withMediaPresentationDuration(mediaPresentationDuration)
                .withMinimumUpdatePeriod(minimumUpdatePeriod)
                .withMinBufferTime(minBufferTime)
                .withTimeShiftBufferDepth(timeShiftBufferDepth)
                .withSuggestedPresentationDelay(suggestedPresentationDelay)
                .withMaxSegmentDuration(maxSegmentDuration)
                .withMaxSubsegmentDuration(maxSubsegmentDuration)
                .withSchemaLocation(schemaLocation);
    }

    public static class Builder {
        private List<ProgramInformation> programInformations;
        private List<BaseURL> baseURLs;
        private List<String> locations;
        private List<Period> periods;
        private List<Metrics> metrics;
        private List<Descriptor> essentialProperties;
        private List<Descriptor> supplementalProperties;
        private List<Descriptor> utcTimings;
        private String id;
        private String profiles;
        private PresentationType type;
        private OffsetDateTime availabilityStartTime;
        private OffsetDateTime availabilityEndTime;
        private OffsetDateTime publishTime;
        private Duration mediaPresentationDuration;
        private Duration minimumUpdatePeriod;
        private Duration minBufferTime;
        private Duration timeShiftBufferDepth;
        private Duration suggestedPresentationDelay;
        private Duration maxSegmentDuration;
        private Duration maxSubsegmentDuration;
        private String schemaLocation;

        public Builder withProgramInformations(List<ProgramInformation> programInformations) {
            this.programInformations = programInformations;
            return this;
        }

        public Builder withBaseURLs(List<BaseURL> baseURLs) {
            this.baseURLs = baseURLs;
            return this;
        }

        public Builder withLocations(List<String> locations) {
            this.locations = locations;
            return this;
        }

        public Builder withPeriods(List<Period> periods) {
            this.periods = periods;
            return this;
        }

        public Builder withMetrics(List<Metrics> metrics) {
            this.metrics = metrics;
            return this;
        }

        public Builder withEssentialProperties(List<Descriptor> essentialProperties) {
            this.essentialProperties = essentialProperties;
            return this;
        }

        public Builder withSupplementalProperties(List<Descriptor> supplementalProperties) {
            this.supplementalProperties = supplementalProperties;
            return this;
        }

        public Builder withUtcTimings(List<Descriptor> utcTimings) {
            this.utcTimings = utcTimings;
            return this;
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withProfiles(String profiles) {
            this.profiles = profiles;
            return this;
        }

        public Builder withType(PresentationType type) {
            this.type = type;
            return this;
        }

        public Builder withAvailabilityStartTime(OffsetDateTime availabilityStartTime) {
            this.availabilityStartTime = availabilityStartTime;
            return this;
        }

        public Builder withAvailabilityEndTime(OffsetDateTime availabilityEndTime) {
            this.availabilityEndTime = availabilityEndTime;
            return this;
        }

        public Builder withPublishTime(OffsetDateTime publishTime) {
            this.publishTime = publishTime;
            return this;
        }

        public Builder withMediaPresentationDuration(Duration mediaPresentationDuration) {
            this.mediaPresentationDuration = mediaPresentationDuration;
            return this;
        }

        public Builder withMinimumUpdatePeriod(Duration minimumUpdatePeriod) {
            this.minimumUpdatePeriod = minimumUpdatePeriod;
            return this;
        }

        public Builder withMinBufferTime(Duration minBufferTime) {
            this.minBufferTime = minBufferTime;
            return this;
        }

        public Builder withTimeShiftBufferDepth(Duration timeShiftBufferDepth) {
            this.timeShiftBufferDepth = timeShiftBufferDepth;
            return this;
        }

        public Builder withSuggestedPresentationDelay(Duration suggestedPresentationDelay) {
            this.suggestedPresentationDelay = suggestedPresentationDelay;
            return this;
        }

        public Builder withMaxSegmentDuration(Duration maxSegmentDuration) {
            this.maxSegmentDuration = maxSegmentDuration;
            return this;
        }

        public Builder withMaxSubsegmentDuration(Duration maxSubsegmentDuration) {
            this.maxSubsegmentDuration = maxSubsegmentDuration;
            return this;
        }

        public Builder withSchemaLocation(String schemaLocation) {
            this.schemaLocation = schemaLocation;
            return this;
        }

        public MPD build() {
            return new MPD(programInformations, baseURLs, locations, periods, metrics, essentialProperties, supplementalProperties, utcTimings, id, profiles, type, availabilityStartTime, availabilityEndTime, publishTime, mediaPresentationDuration, minimumUpdatePeriod, minBufferTime, timeShiftBufferDepth, suggestedPresentationDelay, maxSegmentDuration, maxSubsegmentDuration, schemaLocation);
        }
    }
}

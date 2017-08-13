package io.lindstrom.mpd.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.lindstrom.mpd.support.Utils;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import java.util.List;
import java.util.Objects;

@XmlType(propOrder = {
        "initialization",
        "media",
        "startNumber",
        "timescale",
        "duration",

        "Initialization",
        "RepresentationIndex",
        "segmentTimeline",
        "BitstreamSwitching"
})
public class SegmentTemplate {
    @XmlElement(name = "S", namespace = MPD.NAMESPACE)
    @XmlElementWrapper(name = "SegmentTimeline", namespace = MPD.NAMESPACE)
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private final List<Segment> segmentTimeline;

    @XmlElement(name = "BitstreamSwitching", namespace = MPD.NAMESPACE)
    private final URLType bitstreamswitchingElement;

    @XmlElement(name = "Initialization", namespace = MPD.NAMESPACE)
    private final URLType initialization;

    @XmlElement(name = "RepresentationIndex", namespace = MPD.NAMESPACE)
    private final URLType representationIndex;

    @XmlAttribute(name = "media")
    private final String media;

    @XmlAttribute(name = "index")
    private final String index;

    @XmlAttribute(name = "initialization")
    private final String initializationAttribute;

    @XmlAttribute(name = "bitstreamSwitching")
    private final String bitstreamSwitching;

    @XmlAttribute(name = "duration")
    private final Long duration;

    @XmlAttribute(name = "startNumber")
    private final Long startNumber;

    @XmlAttribute(name = "timescale")
    private final Long timescale;

    @XmlAttribute(name = "presentationTimeOffset")
    private final Long presentationTimeOffset;

    @XmlAttribute(name = "indexRange")
    private final String indexRange;

    @XmlAttribute(name = "indexRangeExact")
    private final Boolean indexRangeExact;

    @XmlAttribute(name = "availabilityTimeOffset")
    private final Double availabilityTimeOffset;

    @XmlAttribute(name = "availabilityTimeComplete")
    private final Boolean availabilityTimeComplete;

    private SegmentTemplate(List<Segment> segmentTimeline, URLType bitstreamswitchingElement, URLType initialization, URLType representationIndex, String media, String index, String initializationAttribute, String bitstreamSwitching, Long duration, Long startNumber, Long timescale, Long presentationTimeOffset, String indexRange, Boolean indexRangeExact, Double availabilityTimeOffset, Boolean availabilityTimeComplete) {
        this.segmentTimeline = segmentTimeline;
        this.bitstreamswitchingElement = bitstreamswitchingElement;
        this.initialization = initialization;
        this.representationIndex = representationIndex;
        this.media = media;
        this.index = index;
        this.initializationAttribute = initializationAttribute;
        this.bitstreamSwitching = bitstreamSwitching;
        this.duration = duration;
        this.startNumber = startNumber;
        this.timescale = timescale;
        this.presentationTimeOffset = presentationTimeOffset;
        this.indexRange = indexRange;
        this.indexRangeExact = indexRangeExact;
        this.availabilityTimeOffset = availabilityTimeOffset;
        this.availabilityTimeComplete = availabilityTimeComplete;
    }

    @SuppressWarnings("unused")
    private SegmentTemplate() {
        this.segmentTimeline = null;
        this.bitstreamswitchingElement = null;
        this.initialization = null;
        this.representationIndex = null;
        this.media = null;
        this.index = null;
        this.initializationAttribute = null;
        this.bitstreamSwitching = null;
        this.duration = null;
        this.startNumber = null;
        this.timescale = null;
        this.presentationTimeOffset = null;
        this.indexRange = null;
        this.indexRangeExact = null;
        this.availabilityTimeOffset = null;
        this.availabilityTimeComplete = null;
    }

    public List<Segment> getSegmentTimeline() {
        return Utils.unmodifiableList(segmentTimeline);
    }

    public URLType getBitstreamswitchingElement() {
        return bitstreamswitchingElement;
    }

    public URLType getInitialization() {
        return initialization;
    }

    public URLType getRepresentationIndex() {
        return representationIndex;
    }

    public String getMedia() {
        return media;
    }

    public String getIndex() {
        return index;
    }

    public String getInitializationAttribute() {
        return initializationAttribute;
    }

    public String getBitstreamSwitching() {
        return bitstreamSwitching;
    }

    public Long getDuration() {
        return duration;
    }

    public Long getStartNumber() {
        return startNumber;
    }

    public Long getTimescale() {
        return timescale;
    }

    public Long getPresentationTimeOffset() {
        return presentationTimeOffset;
    }

    public String getIndexRange() {
        return indexRange;
    }

    public Boolean getIndexRangeExact() {
        return indexRangeExact;
    }

    public Double getAvailabilityTimeOffset() {
        return availabilityTimeOffset;
    }

    public Boolean getAvailabilityTimeComplete() {
        return availabilityTimeComplete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SegmentTemplate that = (SegmentTemplate) o;
        return Objects.equals(segmentTimeline, that.segmentTimeline) &&
                Objects.equals(bitstreamswitchingElement, that.bitstreamswitchingElement) &&
                Objects.equals(initialization, that.initialization) &&
                Objects.equals(representationIndex, that.representationIndex) &&
                Objects.equals(media, that.media) &&
                Objects.equals(index, that.index) &&
                Objects.equals(initializationAttribute, that.initializationAttribute) &&
                Objects.equals(bitstreamSwitching, that.bitstreamSwitching) &&
                Objects.equals(duration, that.duration) &&
                Objects.equals(startNumber, that.startNumber) &&
                Objects.equals(timescale, that.timescale) &&
                Objects.equals(presentationTimeOffset, that.presentationTimeOffset) &&
                Objects.equals(indexRange, that.indexRange) &&
                Objects.equals(indexRangeExact, that.indexRangeExact) &&
                Objects.equals(availabilityTimeOffset, that.availabilityTimeOffset) &&
                Objects.equals(availabilityTimeComplete, that.availabilityTimeComplete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(segmentTimeline, bitstreamswitchingElement, initialization, representationIndex, media, index, initializationAttribute, bitstreamSwitching, duration, startNumber, timescale, presentationTimeOffset, indexRange, indexRangeExact, availabilityTimeOffset, availabilityTimeComplete);
    }

    @Override
    public String toString() {
        return "SegmentTemplate{" +
                "segmentTimeline=" + segmentTimeline +
                ", bitstreamswitchingElement=" + bitstreamswitchingElement +
                ", initialization=" + initialization +
                ", representationIndex=" + representationIndex +
                ", media='" + media + '\'' +
                ", index='" + index + '\'' +
                ", initializationAttribute='" + initializationAttribute + '\'' +
                ", bitstreamSwitching='" + bitstreamSwitching + '\'' +
                ", duration=" + duration +
                ", startNumber=" + startNumber +
                ", timescale=" + timescale +
                ", presentationTimeOffset=" + presentationTimeOffset +
                ", indexRange='" + indexRange + '\'' +
                ", indexRangeExact=" + indexRangeExact +
                ", availabilityTimeOffset=" + availabilityTimeOffset +
                ", availabilityTimeComplete=" + availabilityTimeComplete +
                '}';
    }

    public Builder buildUpon() {
        return new Builder()
            .withSegmentTimeline(segmentTimeline)
            .withBitstreamswitchingElement(bitstreamswitchingElement)
            .withInitialization(initialization)
            .withRepresentationIndex(representationIndex)
            .withMedia(media)
            .withIndex(index)
            .withInitializationAttribute(initializationAttribute)
            .withBitstreamSwitching(bitstreamSwitching)
            .withDuration(duration)
            .withStartNumber(startNumber)
            .withTimescale(timescale)
            .withPresentationTimeOffset(presentationTimeOffset)
            .withIndexRange(indexRange)
            .withIndexRangeExact(indexRangeExact)
            .withAvailabilityTimeOffset(availabilityTimeOffset)
            .withAvailabilityTimeComplete(availabilityTimeComplete);

    }

    public static class Builder {
        private List<Segment> segmentTimeline;
        private URLType bitstreamswitchingElement;
        private URLType initialization;
        private URLType representationIndex;
        private String media;
        private String index;
        private String initializationAttribute;
        private String bitstreamSwitching;
        private Long duration;
        private Long startNumber;
        private Long timescale;
        private Long presentationTimeOffset;
        private String indexRange;
        private Boolean indexRangeExact;
        private Double availabilityTimeOffset;
        private Boolean availabilityTimeComplete;

        public Builder withSegmentTimeline(List<Segment> segmentTimeline) {
            this.segmentTimeline = segmentTimeline;
            return this;
        }

        public Builder withBitstreamswitchingElement(URLType bitstreamswitchingElement) {
            this.bitstreamswitchingElement = bitstreamswitchingElement;
            return this;
        }

        public Builder withInitialization(URLType initialization) {
            this.initialization = initialization;
            return this;
        }

        public Builder withRepresentationIndex(URLType representationIndex) {
            this.representationIndex = representationIndex;
            return this;
        }

        public Builder withMedia(String media) {
            this.media = media;
            return this;
        }

        public Builder withIndex(String index) {
            this.index = index;
            return this;
        }

        public Builder withInitializationAttribute(String initializationAttribute) {
            this.initializationAttribute = initializationAttribute;
            return this;
        }

        public Builder withBitstreamSwitching(String bitstreamSwitching) {
            this.bitstreamSwitching = bitstreamSwitching;
            return this;
        }

        public Builder withDuration(Long duration) {
            this.duration = duration;
            return this;
        }

        public Builder withStartNumber(Long startNumber) {
            this.startNumber = startNumber;
            return this;
        }

        public Builder withTimescale(Long timescale) {
            this.timescale = timescale;
            return this;
        }

        public Builder withPresentationTimeOffset(Long presentationTimeOffset) {
            this.presentationTimeOffset = presentationTimeOffset;
            return this;
        }

        public Builder withIndexRange(String indexRange) {
            this.indexRange = indexRange;
            return this;
        }

        public Builder withIndexRangeExact(Boolean indexRangeExact) {
            this.indexRangeExact = indexRangeExact;
            return this;
        }

        public Builder withAvailabilityTimeOffset(Double availabilityTimeOffset) {
            this.availabilityTimeOffset = availabilityTimeOffset;
            return this;
        }

        public Builder withAvailabilityTimeComplete(Boolean availabilityTimeComplete) {
            this.availabilityTimeComplete = availabilityTimeComplete;
            return this;
        }

        public SegmentTemplate build() {
            return new SegmentTemplate(segmentTimeline, bitstreamswitchingElement, initialization, representationIndex, media, index, initializationAttribute, bitstreamSwitching, duration, startNumber, timescale, presentationTimeOffset, indexRange, indexRangeExact, availabilityTimeOffset, availabilityTimeComplete);
        }
    }
}

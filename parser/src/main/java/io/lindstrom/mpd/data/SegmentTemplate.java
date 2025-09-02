package io.lindstrom.mpd.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.lindstrom.mpd.support.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@JsonPropertyOrder({
        "initialization",
        "media",
        "startNumber",
        "endNumber",
        "timescale",
        "duration",

        "Initialization",
        "RepresentationIndex",
        "segmentTimeline",
        "BitstreamSwitching"
})
public class SegmentTemplate {
    @JacksonXmlProperty(localName = "S", namespace = MPD.NAMESPACE)
    @JacksonXmlElementWrapper(localName = "SegmentTimeline", namespace = MPD.NAMESPACE)
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private final List<Segment> segmentTimeline;

    @JacksonXmlProperty(localName = "BitstreamSwitching", namespace = MPD.NAMESPACE)
    private final URLType bitstreamswitchingElement;

    @JacksonXmlProperty(localName = "Initialization", namespace = MPD.NAMESPACE)
    private final URLType initializationElement;

    @JacksonXmlProperty(localName = "RepresentationIndex", namespace = MPD.NAMESPACE)
    private final URLType representationIndex;

    @JacksonXmlProperty(isAttribute = true)
    private final String media;

    @JacksonXmlProperty(isAttribute = true)
    private final String index;

    @JacksonXmlProperty(isAttribute = true)
    private final String initialization;

    @JacksonXmlProperty(isAttribute = true)
    private final String bitstreamSwitching;

    @JacksonXmlProperty(isAttribute = true)
    private final Long duration;

    @JacksonXmlProperty(isAttribute = true)
    private final Long startNumber;

    @JacksonXmlProperty(isAttribute = true)
    private final Long endNumber;

    @JacksonXmlProperty(isAttribute = true)
    private final Long timescale;

    @JacksonXmlProperty(isAttribute = true)
    private final Long presentationTimeOffset;

    @JacksonXmlProperty(isAttribute = true)
    private final String indexRange;

    @JacksonXmlProperty(isAttribute = true)
    private final Boolean indexRangeExact;

    @JacksonXmlProperty(isAttribute = true)
    private final Double availabilityTimeOffset;

    @JacksonXmlProperty(isAttribute = true)
    private final Boolean availabilityTimeComplete;

    private SegmentTemplate(List<Segment> segmentTimeline, URLType bitstreamswitchingElement, URLType initializationElement, URLType representationIndex, String media, String index, String initialization, String bitstreamSwitching, Long duration, Long startNumber, Long endNumber, Long timescale, Long presentationTimeOffset, String indexRange, Boolean indexRangeExact, Double availabilityTimeOffset, Boolean availabilityTimeComplete) {
        this.segmentTimeline = segmentTimeline;
        this.bitstreamswitchingElement = bitstreamswitchingElement;
        this.initializationElement = initializationElement;
        this.representationIndex = representationIndex;
        this.media = media;
        this.index = index;
        this.initialization = initialization;
        this.bitstreamSwitching = bitstreamSwitching;
        this.duration = duration;
        this.startNumber = startNumber;
        this.endNumber = endNumber;
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
        this.initializationElement = null;
        this.representationIndex = null;
        this.media = null;
        this.index = null;
        this.initialization = null;
        this.bitstreamSwitching = null;
        this.duration = null;
        this.startNumber = null;
        this.endNumber = null;
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

    public URLType getInitializationElement() {
        return initializationElement;
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

    public String getInitialization() {
        return initialization;
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

    public Long getEndNumber() {
        return endNumber;
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
                Objects.equals(initializationElement, that.initializationElement) &&
                Objects.equals(representationIndex, that.representationIndex) &&
                Objects.equals(media, that.media) &&
                Objects.equals(index, that.index) &&
                Objects.equals(initialization, that.initialization) &&
                Objects.equals(bitstreamSwitching, that.bitstreamSwitching) &&
                Objects.equals(duration, that.duration) &&
                Objects.equals(startNumber, that.startNumber) &&
                Objects.equals(endNumber, that.endNumber) &&
                Objects.equals(timescale, that.timescale) &&
                Objects.equals(presentationTimeOffset, that.presentationTimeOffset) &&
                Objects.equals(indexRange, that.indexRange) &&
                Objects.equals(indexRangeExact, that.indexRangeExact) &&
                Objects.equals(availabilityTimeOffset, that.availabilityTimeOffset) &&
                Objects.equals(availabilityTimeComplete, that.availabilityTimeComplete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(segmentTimeline, bitstreamswitchingElement, initializationElement, representationIndex, media, index, initialization, bitstreamSwitching, duration, startNumber, endNumber, timescale, presentationTimeOffset, indexRange, indexRangeExact, availabilityTimeOffset, availabilityTimeComplete);
    }

    @Override
    public String toString() {
        return "SegmentTemplate{" +
                "segmentTimeline=" + segmentTimeline +
                ", bitstreamswitchingElement=" + bitstreamswitchingElement +
                ", initializationElement=" + initializationElement +
                ", representationIndex=" + representationIndex +
                ", media='" + media + '\'' +
                ", index='" + index + '\'' +
                ", initialization='" + initialization + '\'' +
                ", bitstreamSwitching='" + bitstreamSwitching + '\'' +
                ", duration=" + duration +
                ", startNumber=" + startNumber +
                ", endNumber=" + endNumber +
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
            .withInitializationElement(initializationElement)
            .withRepresentationIndex(representationIndex)
            .withMedia(media)
            .withIndex(index)
            .withInitialization(initialization)
            .withBitstreamSwitching(bitstreamSwitching)
            .withDuration(duration)
            .withStartNumber(startNumber)
            .withEndNumber(endNumber)
            .withTimescale(timescale)
            .withPresentationTimeOffset(presentationTimeOffset)
            .withIndexRange(indexRange)
            .withIndexRangeExact(indexRangeExact)
            .withAvailabilityTimeOffset(availabilityTimeOffset)
            .withAvailabilityTimeComplete(availabilityTimeComplete);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<Segment> segmentTimeline;
        private URLType bitstreamswitchingElement;
        private URLType initializationElement;
        private URLType representationIndex;
        private String media;
        private String index;
        private String initialization;
        private String bitstreamSwitching;
        private Long duration;
        private Long startNumber;
        private Long endNumber;
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

        public Builder withSegmentTimeline(Segment ...segment) {
            this.segmentTimeline = Arrays.asList(segment);
            return this;
        }

        public Builder withBitstreamswitchingElement(URLType bitstreamswitchingElement) {
            this.bitstreamswitchingElement = bitstreamswitchingElement;
            return this;
        }

        public Builder withInitializationElement(URLType initializationElement) {
            this.initializationElement = initializationElement;
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

        public Builder withInitialization(String initialization) {
            this.initialization = initialization;
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

        public Builder withEndNumber(Long endNumber) {
            this.endNumber = endNumber;
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
            return new SegmentTemplate(segmentTimeline, bitstreamswitchingElement, initializationElement, representationIndex, media, index, initialization, bitstreamSwitching, duration, startNumber, endNumber, timescale, presentationTimeOffset, indexRange, indexRangeExact, availabilityTimeOffset, availabilityTimeComplete);
        }
    }
}
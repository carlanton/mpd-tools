package io.lindstrom.mpd.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.lindstrom.mpd.support.Utils;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;
import java.util.Objects;

public class SegmentList {
    @XmlElement(name = "Initialization", namespace = MPD.NAMESPACE)
    private final URLType initialization;

    @XmlElement(name = "RepresentationIndex", namespace = MPD.NAMESPACE)
    private final URLType representationIndex;

    @XmlElement(name = "S", namespace = MPD.NAMESPACE)
    @XmlElementWrapper(name = "SegmentTimeline", namespace = MPD.NAMESPACE)
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private final List<Segment> segmentTimeline;

    @XmlElement(name = "BitstreamSwitching", namespace = MPD.NAMESPACE)
    private final URLType bitstreamswitchingElement;

    @XmlElement(name = "SegmentURL", namespace = MPD.NAMESPACE)
    private final List<SegmentURL> segmentURLs;

    @XmlAttribute(name = "duration")
    private final Long duration;

    @XmlAttribute(name = "startNumber")
    private final Long startNumber;

    @XmlAttribute(name = "href", namespace = "http://www.w3.org/1999/xlink")
    private final String href;

    @XmlAttribute(name = "actuate", namespace = "http://www.w3.org/1999/xlink")
    private final ActuateType actuate;

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

    private SegmentList(URLType initialization, URLType representationIndex, List<Segment> segmentTimeline, URLType bitstreamswitchingElement, List<SegmentURL> segmentURLs, Long duration, Long startNumber, String href, ActuateType actuate, Long timescale, Long presentationTimeOffset, String indexRange, Boolean indexRangeExact, Double availabilityTimeOffset, Boolean availabilityTimeComplete) {
        this.initialization = initialization;
        this.representationIndex = representationIndex;
        this.segmentTimeline = segmentTimeline;
        this.bitstreamswitchingElement = bitstreamswitchingElement;
        this.segmentURLs = segmentURLs;
        this.duration = duration;
        this.startNumber = startNumber;
        this.href = href;
        this.actuate = actuate;
        this.timescale = timescale;
        this.presentationTimeOffset = presentationTimeOffset;
        this.indexRange = indexRange;
        this.indexRangeExact = indexRangeExact;
        this.availabilityTimeOffset = availabilityTimeOffset;
        this.availabilityTimeComplete = availabilityTimeComplete;
    }

    @SuppressWarnings("unused")
    private SegmentList() {
        this.initialization = null;
        this.representationIndex = null;
        this.segmentTimeline = null;
        this.bitstreamswitchingElement = null;
        this.segmentURLs = null;
        this.duration = null;
        this.startNumber = null;
        this.href = null;
        this.actuate = null;
        this.timescale = null;
        this.presentationTimeOffset = null;
        this.indexRange = null;
        this.indexRangeExact = null;
        this.availabilityTimeOffset = null;
        this.availabilityTimeComplete = null;
    }

    public URLType getInitialization() {
        return initialization;
    }

    public URLType getRepresentationIndex() {
        return representationIndex;
    }

    public List<Segment> getSegmentTimeline() {
        return Utils.unmodifiableList(segmentTimeline);
    }

    public URLType getBitstreamswitchingElement() {
        return bitstreamswitchingElement;
    }

    public List<SegmentURL> getSegmentURLs() {
        return Utils.unmodifiableList(segmentURLs);
    }

    public Long getDuration() {
        return duration;
    }

    public Long getStartNumber() {
        return startNumber;
    }

    public String getHref() {
        return href;
    }

    public ActuateType getActuate() {
        return actuate;
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
        SegmentList that = (SegmentList) o;
        return Objects.equals(initialization, that.initialization) &&
                Objects.equals(representationIndex, that.representationIndex) &&
                Objects.equals(segmentTimeline, that.segmentTimeline) &&
                Objects.equals(bitstreamswitchingElement, that.bitstreamswitchingElement) &&
                Objects.equals(segmentURLs, that.segmentURLs) &&
                Objects.equals(duration, that.duration) &&
                Objects.equals(startNumber, that.startNumber) &&
                Objects.equals(href, that.href) &&
                actuate == that.actuate &&
                Objects.equals(timescale, that.timescale) &&
                Objects.equals(presentationTimeOffset, that.presentationTimeOffset) &&
                Objects.equals(indexRange, that.indexRange) &&
                Objects.equals(indexRangeExact, that.indexRangeExact) &&
                Objects.equals(availabilityTimeOffset, that.availabilityTimeOffset) &&
                Objects.equals(availabilityTimeComplete, that.availabilityTimeComplete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(initialization, representationIndex, segmentTimeline, bitstreamswitchingElement, segmentURLs, duration, startNumber, href, actuate, timescale, presentationTimeOffset, indexRange, indexRangeExact, availabilityTimeOffset, availabilityTimeComplete);
    }

    @Override
    public String toString() {
        return "SegmentList{" +
                "initialization=" + initialization +
                ", representationIndex=" + representationIndex +
                ", segmentTimeline=" + segmentTimeline +
                ", bitstreamswitchingElement=" + bitstreamswitchingElement +
                ", segmentURLs=" + segmentURLs +
                ", duration=" + duration +
                ", startNumber=" + startNumber +
                ", href='" + href + '\'' +
                ", actuate=" + actuate +
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
            .withInitialization(initialization)
            .withRepresentationIndex(representationIndex)
            .withSegmentTimeline(segmentTimeline)
            .withBitstreamswitchingElement(bitstreamswitchingElement)
            .withSegmentURLs(segmentURLs)
            .withDuration(duration)
            .withStartNumber(startNumber)
            .withHref(href)
            .withActuate(actuate)
            .withTimescale(timescale)
            .withPresentationTimeOffset(presentationTimeOffset)
            .withIndexRange(indexRange)
            .withIndexRangeExact(indexRangeExact)
            .withAvailabilityTimeOffset(availabilityTimeOffset)
            .withAvailabilityTimeComplete(availabilityTimeComplete);
    }

    public static class Builder {
        private URLType initialization;
        private URLType representationIndex;
        private List<Segment> segmentTimeline;
        private URLType bitstreamswitchingElement;
        private List<SegmentURL> segmentURLs;
        private Long duration;
        private Long startNumber;
        private String href;
        private ActuateType actuate;
        private Long timescale;
        private Long presentationTimeOffset;
        private String indexRange;
        private Boolean indexRangeExact;
        private Double availabilityTimeOffset;
        private Boolean availabilityTimeComplete;

        public Builder withInitialization(URLType initialization) {
            this.initialization = initialization;
            return this;
        }

        public Builder withRepresentationIndex(URLType representationIndex) {
            this.representationIndex = representationIndex;
            return this;
        }

        public Builder withSegmentTimeline(List<Segment> segmentTimeline) {
            this.segmentTimeline = segmentTimeline;
            return this;
        }

        public Builder withBitstreamswitchingElement(URLType bitstreamswitchingElement) {
            this.bitstreamswitchingElement = bitstreamswitchingElement;
            return this;
        }

        public Builder withSegmentURLs(List<SegmentURL> segmentURLs) {
            this.segmentURLs = segmentURLs;
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

        public Builder withHref(String href) {
            this.href = href;
            return this;
        }

        public Builder withActuate(ActuateType actuate) {
            this.actuate = actuate;
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

        public SegmentList build() {
            return new SegmentList(initialization, representationIndex, segmentTimeline, bitstreamswitchingElement, segmentURLs, duration, startNumber, href, actuate, timescale, presentationTimeOffset, indexRange, indexRangeExact, availabilityTimeOffset, availabilityTimeComplete);
        }
    }
}

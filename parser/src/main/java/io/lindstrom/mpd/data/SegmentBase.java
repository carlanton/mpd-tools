package io.lindstrom.mpd.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class SegmentBase {
    @XmlElement(name = "Initialization", namespace = MPD.NAMESPACE)
    private final URLType initialization;

    @XmlElement(name = "RepresentationIndex", namespace = MPD.NAMESPACE)
    private final URLType representationIndex;

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

    protected SegmentBase(URLType initialization, URLType representationIndex, Long timescale, Long presentationTimeOffset, String indexRange, Boolean indexRangeExact, Double availabilityTimeOffset, Boolean availabilityTimeComplete) {
        this.initialization = initialization;
        this.representationIndex = representationIndex;
        this.timescale = timescale;
        this.presentationTimeOffset = presentationTimeOffset;
        this.indexRange = indexRange;
        this.indexRangeExact = indexRangeExact;
        this.availabilityTimeOffset = availabilityTimeOffset;
        this.availabilityTimeComplete = availabilityTimeComplete;
    }

    @SuppressWarnings("unused")
    protected SegmentBase() {
        this.initialization = null;
        this.representationIndex = null;
        this.timescale = null;
        this.presentationTimeOffset = null;
        this.indexRange = null;
        this.indexRangeExact = null;
        this.availabilityTimeOffset = null;
        this.availabilityTimeComplete = null;
    }

    public Builder buildUpon() {
        return buildUpon(new Builder());
    }

    <T extends AbstractBuilder<T>> T buildUpon(T builder) {
        return builder.withInitialization(initialization)
                .withRepresentationIndex(representationIndex)
                .withTimescale(timescale)
                .withPresentationTimeOffset(presentationTimeOffset)
                .withIndexRange(indexRange)
                .withIndexRangeExact(indexRangeExact)
                .withAvailabilityTimeOffset(availabilityTimeOffset)
                .withAvailabilityTimeComplete(availabilityTimeComplete);
    }
    
    static abstract class AbstractBuilder<T> {
        URLType initialization;
        URLType representationIndex;
        Long timescale;
        Long presentationTimeOffset;
        String indexRange;
        Boolean indexRangeExact;
        Double availabilityTimeOffset;
        Boolean availabilityTimeComplete;

        public T withInitialization(URLType initialization) {
            this.initialization = initialization;
            return getThis();
        }

        public T withRepresentationIndex(URLType representationIndex) {
            this.representationIndex = representationIndex;
            return getThis();
        }

        public T withTimescale(Long timescale) {
            this.timescale = timescale;
            return getThis();
        }

        public T withPresentationTimeOffset(Long presentationTimeOffset) {
            this.presentationTimeOffset = presentationTimeOffset;
            return getThis();
        }

        public T withIndexRange(String indexRange) {
            this.indexRange = indexRange;
            return getThis();
        }

        public T withIndexRangeExact(Boolean indexRangeExact) {
            this.indexRangeExact = indexRangeExact;
            return getThis();
        }

        public T withAvailabilityTimeOffset(Double availabilityTimeOffset) {
            this.availabilityTimeOffset = availabilityTimeOffset;
            return getThis();
        }

        public T withAvailabilityTimeComplete(Boolean availabilityTimeComplete) {
            this.availabilityTimeComplete = availabilityTimeComplete;
            return getThis();
        }
        
        abstract T getThis();
    }

    public static class Builder extends AbstractBuilder<Builder> {
        public SegmentBase build() {
            return new SegmentBase(initialization, representationIndex, timescale, presentationTimeOffset, indexRange, indexRangeExact, availabilityTimeOffset, availabilityTimeComplete);
        }

        @Override
        Builder getThis() {
            return this;
        }
    }

}

package io.lindstrom.mpd.data;

import io.lindstrom.mpd.data.descriptor.Descriptor;
import io.lindstrom.mpd.support.Utils;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

@XmlType(propOrder = {
        "id",

        "baseURLs",
        "segmentBase",
        "segmentList",
        "segmentTemplate",
        "assetIdentifier",
        "eventStreams",
        "adaptationSets",
        "subsets",
        "supplementalProperties"
})
public class Period {
    @XmlElement(name = "BaseURL", namespace = MPD.NAMESPACE)
    private final List<BaseURL> baseURLs;

    @XmlElement(name = "SegmentBase", namespace = MPD.NAMESPACE)
    private final SegmentBase segmentBase;

    @XmlElement(name = "SegmentList", namespace = MPD.NAMESPACE)
    private final SegmentList segmentList;

    @XmlElement(name = "SegmentTemplate", namespace = MPD.NAMESPACE)
    private final SegmentTemplate segmentTemplate;

    @XmlElement(name = "AssetIdentifier", namespace = MPD.NAMESPACE)
    private final Descriptor assetIdentifier;

    @XmlElement(name = "EventStream", namespace = MPD.NAMESPACE)
    private final List<EventStream> eventStreams;

    @XmlElement(name = "AdaptationSet", namespace = MPD.NAMESPACE)
    private final List<AdaptationSet> adaptationSets;

    @XmlElement(name = "Subset", namespace = MPD.NAMESPACE)
    private final List<Subset> subsets;

    @XmlElement(name = "SupplementalProperty", namespace = MPD.NAMESPACE)
    private final List<Descriptor> supplementalProperties;

    @XmlAttribute(name = "href", namespace = "http://www.w3.org/1999/xlink")
    private final String href;

    @XmlAttribute(name = "actuate", namespace = "http://www.w3.org/1999/xlink")
    private final ActuateType actuate;

    @XmlAttribute(name = "id")
    private final String id;

    @XmlAttribute(name = "start")
    private final Duration start;

    @XmlAttribute(name = "duration")
    private final Duration duration;

    @XmlAttribute(name = "bitstreamSwitching")
    private final Boolean bitstreamSwitching;

    private Period(List<BaseURL> baseURLs, SegmentBase segmentBase, SegmentList segmentList, SegmentTemplate segmentTemplate, Descriptor assetIdentifier, List<EventStream> eventStreams, List<AdaptationSet> adaptationSets, List<Subset> subsets, List<Descriptor> supplementalProperties, String href, ActuateType actuate, String id, Duration start, Duration duration, Boolean bitstreamSwitching) {
        this.baseURLs = baseURLs;
        this.segmentBase = segmentBase;
        this.segmentList = segmentList;
        this.segmentTemplate = segmentTemplate;
        this.assetIdentifier = assetIdentifier;
        this.eventStreams = eventStreams;
        this.adaptationSets = adaptationSets;
        this.subsets = subsets;
        this.supplementalProperties = supplementalProperties;
        this.href = href;
        this.actuate = actuate;
        this.id = id;
        this.start = start;
        this.duration = duration;
        this.bitstreamSwitching = bitstreamSwitching;
    }

    @SuppressWarnings("unused")
    private Period() {
        this.baseURLs = null;
        this.segmentBase = null;
        this.segmentList = null;
        this.segmentTemplate = null;
        this.assetIdentifier = null;
        this.eventStreams = null;
        this.adaptationSets = null;
        this.subsets = null;
        this.supplementalProperties = null;
        this.href = null;
        this.actuate = null;
        this.id = null;
        this.start = null;
        this.duration = null;
        this.bitstreamSwitching = null;
    }

    public List<BaseURL> getBaseURLs() {
        return Utils.unmodifiableList(baseURLs);
    }

    public SegmentBase getSegmentBase() {
        return segmentBase;
    }

    public SegmentList getSegmentList() {
        return segmentList;
    }

    public SegmentTemplate getSegmentTemplate() {
        return segmentTemplate;
    }

    public Descriptor getAssetIdentifier() {
        return assetIdentifier;
    }

    public List<EventStream> getEventStreams() {
        return Utils.unmodifiableList(eventStreams);
    }

    public List<AdaptationSet> getAdaptationSets() {
        return Utils.unmodifiableList(adaptationSets);
    }

    public List<Subset> getSubsets() {
        return Utils.unmodifiableList(subsets);
    }

    public List<Descriptor> getSupplementalProperties() {
        return Utils.unmodifiableList(supplementalProperties);
    }

    public String getHref() {
        return href;
    }

    public ActuateType getActuate() {
        return actuate;
    }

    public String getId() {
        return id;
    }

    public Duration getStart() {
        return start;
    }

    public Duration getDuration() {
        return duration;
    }

    public Boolean getBitstreamSwitching() {
        return bitstreamSwitching;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return Objects.equals(baseURLs, period.baseURLs) &&
                Objects.equals(segmentBase, period.segmentBase) &&
                Objects.equals(segmentList, period.segmentList) &&
                Objects.equals(segmentTemplate, period.segmentTemplate) &&
                Objects.equals(assetIdentifier, period.assetIdentifier) &&
                Objects.equals(eventStreams, period.eventStreams) &&
                Objects.equals(adaptationSets, period.adaptationSets) &&
                Objects.equals(subsets, period.subsets) &&
                Objects.equals(supplementalProperties, period.supplementalProperties) &&
                Objects.equals(href, period.href) &&
                actuate == period.actuate &&
                Objects.equals(id, period.id) &&
                Objects.equals(start, period.start) &&
                Objects.equals(duration, period.duration) &&
                Objects.equals(bitstreamSwitching, period.bitstreamSwitching);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseURLs, segmentBase, segmentList, segmentTemplate, assetIdentifier, eventStreams, adaptationSets, subsets, supplementalProperties, href, actuate, id, start, duration, bitstreamSwitching);
    }

    @Override
    public String toString() {
        return "Period{" +
                "baseURLs=" + baseURLs +
                ", segmentBase=" + segmentBase +
                ", segmentList=" + segmentList +
                ", segmentTemplate=" + segmentTemplate +
                ", assetIdentifier=" + assetIdentifier +
                ", eventStreams=" + eventStreams +
                ", adaptationSets=" + adaptationSets +
                ", subsets=" + subsets +
                ", supplementalProperties=" + supplementalProperties +
                ", href='" + href + '\'' +
                ", actuate=" + actuate +
                ", id='" + id + '\'' +
                ", start=" + start +
                ", duration=" + duration +
                ", bitstreamSwitching=" + bitstreamSwitching +
                '}';
    }

    public Builder buildUpon() {
        return new Builder()
                .withBaseURLs(baseURLs)
                .withSegmentBase(segmentBase)
                .withSegmentList(segmentList)
                .withSegmentTemplate(segmentTemplate)
                .withAssetIdentifier(assetIdentifier)
                .withEventStreams(eventStreams)
                .withAdaptationSets(adaptationSets)
                .withSubsets(subsets)
                .withSupplementalProperties(supplementalProperties)
                .withHref(href)
                .withActuate(actuate)
                .withId(id)
                .withStart(start)
                .withDuration(duration)
                .withBitstreamSwitching(bitstreamSwitching);
    }

    public static class Builder {
        private List<BaseURL> baseURLs;
        private SegmentBase segmentBase;
        private SegmentList segmentList;
        private SegmentTemplate segmentTemplate;
        private Descriptor assetIdentifier;
        private List<EventStream> eventStreams;
        private List<AdaptationSet> adaptationSets;
        private List<Subset> subsets;
        private List<Descriptor> supplementalProperties;
        private String href;
        private ActuateType actuate;
        private String id;
        private Duration start;
        private Duration duration;
        private Boolean bitstreamSwitching;

        public Builder withBaseURLs(List<BaseURL> baseURLs) {
            this.baseURLs = baseURLs;
            return this;
        }

        public Builder withSegmentBase(SegmentBase segmentBase) {
            this.segmentBase = segmentBase;
            return this;
        }

        public Builder withSegmentList(SegmentList segmentList) {
            this.segmentList = segmentList;
            return this;
        }

        public Builder withSegmentTemplate(SegmentTemplate segmentTemplate) {
            this.segmentTemplate = segmentTemplate;
            return this;
        }

        public Builder withAssetIdentifier(Descriptor assetIdentifier) {
            this.assetIdentifier = assetIdentifier;
            return this;
        }

        public Builder withEventStreams(List<EventStream> eventStreams) {
            this.eventStreams = eventStreams;
            return this;
        }

        public Builder withAdaptationSets(List<AdaptationSet> adaptationSets) {
            this.adaptationSets = adaptationSets;
            return this;
        }

        public Builder withAdaptationSet(AdaptationSet adaptationSet, AdaptationSet ...moreAdaptationSets) {
            this.adaptationSets = Utils.varargsToList(adaptationSet, moreAdaptationSets);
            return this;
        }

        public Builder withSubsets(List<Subset> subsets) {
            this.subsets = subsets;
            return this;
        }

        public Builder withSupplementalProperties(List<Descriptor> supplementalProperties) {
            this.supplementalProperties = supplementalProperties;
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

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withStart(Duration start) {
            this.start = start;
            return this;
        }

        public Builder withDuration(Duration duration) {
            this.duration = duration;
            return this;
        }

        public Builder withBitstreamSwitching(Boolean bitstreamSwitching) {
            this.bitstreamSwitching = bitstreamSwitching;
            return this;
        }

        public Period build() {
            return new Period(baseURLs, segmentBase, segmentList, segmentTemplate, assetIdentifier, eventStreams, adaptationSets, subsets, supplementalProperties, href, actuate, id, start, duration, bitstreamSwitching);
        }
    }
}

package io.lindstrom.mpd.data;

import io.lindstrom.mpd.support.VectorAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;
import java.util.Objects;

public class SubRepresentation extends RepresentationBase {
    @XmlAttribute(name = "level")
    private final Long level;

    @XmlAttribute(name = "dependencyLevel")
    @XmlJavaTypeAdapter(VectorAdapter.LongAdapter.class)
    private final List<Long> dependencyLevels;

    @XmlAttribute(name = "bandwidth")
    private final Long bandwidth;

    @XmlAttribute(name = "contentComponent")
    @XmlJavaTypeAdapter(VectorAdapter.StringAdapter.class)
    private final List<String> contentComponents;

    private SubRepresentation(List<Descriptor> framePackings, List<Descriptor> audioChannelConfigurations, List<Descriptor> contentProtections, List<Descriptor> essentialProperties, List<Descriptor> supplementalProperties, List<EventStream> inbandEventStreams, String profiles, Long width, Long height, Ratio sar, FrameRate frameRate, String audioSamplingRate, String mimeType, String segmentProfiles, String codecs, Double maximumSAPPeriod, Long startWithSAP, Double maxPlayoutRate, Boolean codingDependency, VideoScanType scanType, Long level, List<Long> dependencyLevels, Long bandwidth, List<String> contentComponents) {
        super(framePackings, audioChannelConfigurations, contentProtections, essentialProperties, supplementalProperties, inbandEventStreams, profiles, width, height, sar, frameRate, audioSamplingRate, mimeType, segmentProfiles, codecs, maximumSAPPeriod, startWithSAP, maxPlayoutRate, codingDependency, scanType);
        this.level = level;
        this.dependencyLevels = dependencyLevels;
        this.bandwidth = bandwidth;
        this.contentComponents = contentComponents;
    }

    @SuppressWarnings("unused")
    private SubRepresentation() {
        this.level = null;
        this.dependencyLevels = null;
        this.bandwidth = null;
        this.contentComponents = null;
    }

    public Long getLevel() {
        return level;
    }

    public List<Long> getDependencyLevels() {
        return dependencyLevels;
    }

    public Long getBandwidth() {
        return bandwidth;
    }

    public List<String> getContentComponents() {
        return contentComponents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SubRepresentation that = (SubRepresentation) o;
        return Objects.equals(level, that.level) &&
                Objects.equals(dependencyLevels, that.dependencyLevels) &&
                Objects.equals(bandwidth, that.bandwidth) &&
                Objects.equals(contentComponents, that.contentComponents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), level, dependencyLevels, bandwidth, contentComponents);
    }

    @Override
    public String toString() {
        return "SubRepresentation{" +
                "super=" + super.toString() +
                ", level=" + level +
                ", dependencyLevels=" + dependencyLevels +
                ", bandwidth=" + bandwidth +
                ", contentComponents=" + contentComponents +
                '}';
    }

    public Builder buildUpon() {
        return super.buildUpon(new Builder()
                .withLevel(level)
                .withDependencyLevels(dependencyLevels)
                .withBandwidth(bandwidth)
                .withContentComponents(contentComponents));
    }

    public static class Builder extends RepresentationBase.AbstractBuilder<Builder> {
        private Long level;
        private List<Long> dependencyLevels;
        private Long bandwidth;
        private List<String> contentComponents;

        public Builder withLevel(Long level) {
            this.level = level;
            return this;
        }

        public Builder withDependencyLevels(List<Long> dependencyLevels) {
            this.dependencyLevels = dependencyLevels;
            return this;
        }

        public Builder withBandwidth(Long bandwidth) {
            this.bandwidth = bandwidth;
            return this;
        }

        public Builder withContentComponents(List<String> contentComponents) {
            this.contentComponents = contentComponents;
            return this;
        }

        public SubRepresentation build() {
            return new SubRepresentation(framePackings, audioChannelConfigurations, contentProtections, essentialProperties, supplementalProperties, inbandEventStreams, profiles, width, height, sar, frameRate, audioSamplingRate, mimeType, segmentProfiles, codecs, maximumSAPPeriod, startWithSAP, maxPlayoutRate, codingDependency, scanType, level, dependencyLevels, bandwidth, contentComponents);
        }

        @Override
        Builder getThis() {
            return this;
        }
    }
}

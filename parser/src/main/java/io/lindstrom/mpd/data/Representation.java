package io.lindstrom.mpd.data;

import io.lindstrom.mpd.data.descriptor.Descriptor;
import io.lindstrom.mpd.support.Utils;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;
import java.util.Objects;


@XmlType(propOrder = {
        "id",
        "bandwidth",

        "width",
        "height",
        "codecs",

        "framePackings",
        "audioChannelConfigurations",
        "contentProtections",
        "essentialProperties",
        "supplementalProperties",
        "inbandEventStreams",
        "baseURLs",
        "SubRepresentation",
        "segmentBase",
        "segmentList",
        "segmentTemplate"
})
public class Representation extends RepresentationBase {
    @XmlElement(name = "BaseURL", namespace = MPD.NAMESPACE)
    private final List<BaseURL> baseURLs;

    @XmlElement(name = "SubRepresentation", namespace = MPD.NAMESPACE)
    private final List<SubRepresentation> subRepresentations;

    @XmlElement(name = "SegmentBase", namespace = MPD.NAMESPACE)
    private final SegmentBase segmentBase;

    @XmlElement(name = "SegmentList", namespace = MPD.NAMESPACE)
    private final SegmentList segmentList;

    @XmlElement(name = "SegmentTemplate", namespace = MPD.NAMESPACE)
    private final SegmentTemplate segmentTemplate;

    @XmlAttribute(name = "id", required = true)
    private final String id;

    @XmlAttribute(name = "bandwidth", required = true)
    private final long bandwidth;

    @XmlAttribute(name = "qualityRanking")
    private final Long qualityRanking;

    @XmlAttribute(name = "dependencyId")
    private final String dependencyId;

    @XmlAttribute(name = "mediaStreamStructureId")
    private final String mediaStreamStructureId;

    private Representation(List<Descriptor> framePackings, List<Descriptor> audioChannelConfigurations, List<Descriptor> contentProtections, List<Descriptor> essentialProperties, List<Descriptor> supplementalProperties, List<EventStream> inbandEventStreams, String profiles, Long width, Long height, Ratio sar, FrameRate frameRate, String audioSamplingRate, String mimeType, String segmentProfiles, String codecs, Double maximumSAPPeriod, Long startWithSAP, Double maxPlayoutRate, Boolean codingDependency, VideoScanType scanType, List<BaseURL> baseURLs, List<SubRepresentation> subRepresentations, SegmentBase segmentBase, SegmentList segmentList, SegmentTemplate segmentTemplate, String id, long bandwidth, Long qualityRanking, String dependencyId, String mediaStreamStructureId) {
        super(framePackings, audioChannelConfigurations, contentProtections, essentialProperties, supplementalProperties, inbandEventStreams, profiles, width, height, sar, frameRate, audioSamplingRate, mimeType, segmentProfiles, codecs, maximumSAPPeriod, startWithSAP, maxPlayoutRate, codingDependency, scanType);
        this.baseURLs = baseURLs;
        this.subRepresentations = subRepresentations;
        this.segmentBase = segmentBase;
        this.segmentList = segmentList;
        this.segmentTemplate = segmentTemplate;
        this.id = id;
        this.bandwidth = bandwidth;
        this.qualityRanking = qualityRanking;
        this.dependencyId = dependencyId;
        this.mediaStreamStructureId = mediaStreamStructureId;
    }

    @SuppressWarnings("unused")
    private Representation() {
        this.baseURLs = null;
        this.subRepresentations = null;
        this.segmentBase = null;
        this.segmentList = null;
        this.segmentTemplate = null;
        this.id = null;
        this.bandwidth = 0;
        this.qualityRanking = null;
        this.dependencyId = null;
        this.mediaStreamStructureId = null;
    }

    public List<BaseURL> getBaseURLs() {
        return Utils.unmodifiableList(baseURLs);
    }

    public List<SubRepresentation> getSubRepresentations() {
        return Utils.unmodifiableList(subRepresentations);
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

    public String getId() {
        return id;
    }

    public long getBandwidth() {
        return bandwidth;
    }

    public Long getQualityRanking() {
        return qualityRanking;
    }

    public String getDependencyId() {
        return dependencyId;
    }

    public String getMediaStreamStructureId() {
        return mediaStreamStructureId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Representation that = (Representation) o;
        return bandwidth == that.bandwidth &&
                Objects.equals(baseURLs, that.baseURLs) &&
                Objects.equals(subRepresentations, that.subRepresentations) &&
                Objects.equals(segmentBase, that.segmentBase) &&
                Objects.equals(segmentList, that.segmentList) &&
                Objects.equals(segmentTemplate, that.segmentTemplate) &&
                Objects.equals(id, that.id) &&
                Objects.equals(qualityRanking, that.qualityRanking) &&
                Objects.equals(dependencyId, that.dependencyId) &&
                Objects.equals(mediaStreamStructureId, that.mediaStreamStructureId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), baseURLs, subRepresentations, segmentBase, segmentList, segmentTemplate, id, bandwidth, qualityRanking, dependencyId, mediaStreamStructureId);
    }

    @Override
    public String toString() {
        return "Representation{" +
                "super=" + super.toString() +
                ", baseURLs=" + baseURLs +
                ", subRepresentations=" + subRepresentations +
                ", segmentBase=" + segmentBase +
                ", segmentList=" + segmentList +
                ", segmentTemplate=" + segmentTemplate +
                ", id='" + id + '\'' +
                ", bandwidth=" + bandwidth +
                ", qualityRanking=" + qualityRanking +
                ", dependencyId=" + dependencyId +
                ", mediaStreamStructureId=" + mediaStreamStructureId +
                '}';
    }

    public Builder buildUpon() {
        return buildUpon(new Builder()
            .withBaseURLs(baseURLs)
            .withSubRepresentations(subRepresentations)
            .withSegmentBase(segmentBase)
            .withSegmentList(segmentList)
            .withSegmentTemplate(segmentTemplate)
            .withId(id)
            .withBandwidth(bandwidth)
            .withQualityRanking(qualityRanking)
            .withDependencyId(dependencyId)
            .withMediaStreamStructureId(mediaStreamStructureId));
    }



    public static class Builder extends RepresentationBase.AbstractBuilder<Builder> {
        private List<BaseURL> baseURLs;
        private List<SubRepresentation> subRepresentations;
        private SegmentBase segmentBase;
        private SegmentList segmentList;
        private SegmentTemplate segmentTemplate;
        private String id;
        private long bandwidth;
        private Long qualityRanking;
        private String dependencyId;
        private String mediaStreamStructureId;

        @Override
        Builder getThis() {
            return this;
        }

        public Builder withBaseURLs(List<BaseURL> baseURLs) {
            this.baseURLs = baseURLs;
            return this;
        }

        public Builder withSubRepresentations(List<SubRepresentation> subRepresentations) {
            this.subRepresentations = subRepresentations;
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

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withBandwidth(long bandwidth) {
            this.bandwidth = bandwidth;
            return this;
        }

        public Builder withQualityRanking(Long qualityRanking) {
            this.qualityRanking = qualityRanking;
            return this;
        }

        public Builder withDependencyId(String dependencyIds) {
            this.dependencyId = dependencyIds;
            return this;
        }

        public Builder withMediaStreamStructureId(String mediaStreamStructureId) {
            this.mediaStreamStructureId = mediaStreamStructureId;
            return this;
        }

        public Representation build() {
            return new Representation(framePackings, audioChannelConfigurations, contentProtections, essentialProperties, supplementalProperties, inbandEventStreams, profiles, width, height, sar, frameRate, audioSamplingRate, mimeType, segmentProfiles, codecs, maximumSAPPeriod, startWithSAP, maxPlayoutRate, codingDependency, scanType, baseURLs, subRepresentations, segmentBase, segmentList, segmentTemplate, id, bandwidth, qualityRanking, dependencyId, mediaStreamStructureId);
        }
    }
}

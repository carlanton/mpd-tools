package io.lindstrom.mpd.data;

import io.lindstrom.mpd.data.descriptor.Descriptor;
import io.lindstrom.mpd.support.Utils;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;
import java.util.Objects;


@XmlType(propOrder = {
    "framePackings",
    "audioChannelConfigurations",
    "contentProtections",
    "essentialProperties",
    "supplementalProperties",
    "inbandEventStreams"
})
public abstract class RepresentationBase {
    @XmlElement(name = "FramePacking", namespace = MPD.NAMESPACE)
    private final List<Descriptor> framePackings;

    @XmlElement(name = "AudioChannelConfiguration", namespace = MPD.NAMESPACE)
    private final List<Descriptor> audioChannelConfigurations;

    @XmlElement(name = "ContentProtection", namespace = MPD.NAMESPACE)
    private final List<Descriptor> contentProtections;

    @XmlElement(name = "EssentialProperty", namespace = MPD.NAMESPACE)
    private final List<Descriptor> essentialProperties;

    @XmlElement(name = "SupplementalProperty", namespace = MPD.NAMESPACE)
    private final List<Descriptor> supplementalProperties;

    @XmlElement(name = "InbandEventStream", namespace = MPD.NAMESPACE)
    private final List<EventStream> inbandEventStreams;

    @XmlAttribute(name = "profiles")
    private final String profiles;

    @XmlAttribute(name = "width")
    private final Long width;

    @XmlAttribute(name = "height")
    private final Long height;

    @XmlAttribute(name = "sar")
    private final Ratio sar;

    @XmlAttribute(name = "frameRate")
    private final FrameRate frameRate;

    @XmlAttribute(name = "audioSamplingRate")
    private final String audioSamplingRate;

    @XmlAttribute(name = "mimeType")
    private final String mimeType;

    @XmlAttribute(name = "segmentProfiles")
    private final String segmentProfiles;

    @XmlAttribute(name = "codecs")
    private final String codecs;

    @XmlAttribute(name = "maximumSAPPeriod")
    private final Double maximumSAPPeriod;

    @XmlAttribute(name = "startWithSAP")
    private final Long startWithSAP;

    @XmlAttribute(name = "maxPlayoutRate")
    private final Double maxPlayoutRate;

    @XmlAttribute(name = "codingDependency")
    private final Boolean codingDependency;

    @XmlAttribute(name = "scanType")
    private final VideoScanType scanType;

    RepresentationBase(List<Descriptor> framePackings, List<Descriptor> audioChannelConfigurations, List<Descriptor> contentProtections, List<Descriptor> essentialProperties, List<Descriptor> supplementalProperties, List<EventStream> inbandEventStreams, String profiles, Long width, Long height, Ratio sar, FrameRate frameRate, String audioSamplingRate, String mimeType, String segmentProfiles, String codecs, Double maximumSAPPeriod, Long startWithSAP, Double maxPlayoutRate, Boolean codingDependency, VideoScanType scanType) {
        this.framePackings = framePackings;
        this.audioChannelConfigurations = audioChannelConfigurations;
        this.contentProtections = contentProtections;
        this.essentialProperties = essentialProperties;
        this.supplementalProperties = supplementalProperties;
        this.inbandEventStreams = inbandEventStreams;
        this.profiles = profiles;
        this.width = width;
        this.height = height;
        this.sar = sar;
        this.frameRate = frameRate;
        this.audioSamplingRate = audioSamplingRate;
        this.mimeType = mimeType;
        this.segmentProfiles = segmentProfiles;
        this.codecs = codecs;
        this.maximumSAPPeriod = maximumSAPPeriod;
        this.startWithSAP = startWithSAP;
        this.maxPlayoutRate = maxPlayoutRate;
        this.codingDependency = codingDependency;
        this.scanType = scanType;
    }

    RepresentationBase() {
        this.framePackings = null;
        this.audioChannelConfigurations = null;
        this.contentProtections = null;
        this.essentialProperties = null;
        this.supplementalProperties = null;
        this.inbandEventStreams = null;
        this.profiles = null;
        this.width = null;
        this.height = null;
        this.sar = null;
        this.frameRate = null;
        this.audioSamplingRate = null;
        this.mimeType = null;
        this.segmentProfiles = null;
        this.codecs = null;
        this.maximumSAPPeriod = null;
        this.startWithSAP = null;
        this.maxPlayoutRate = null;
        this.codingDependency = null;
        this.scanType = null;
    }

    public List<Descriptor> getFramePackings() {
        return Utils.unmodifiableList(framePackings);
    }

    public List<Descriptor> getAudioChannelConfigurations() {
        return Utils.unmodifiableList(audioChannelConfigurations);
    }

    public List<Descriptor> getContentProtections() {
        return Utils.unmodifiableList(contentProtections);
    }

    public List<Descriptor> getEssentialProperties() {
        return Utils.unmodifiableList(essentialProperties);
    }

    public List<Descriptor> getSupplementalProperties() {
        return Utils.unmodifiableList(supplementalProperties);
    }

    public List<EventStream> getInbandEventStreams() {
        return Utils.unmodifiableList(inbandEventStreams);
    }

    public String getProfiles() {
        return profiles;
    }

    public Long getWidth() {
        return width;
    }

    public Long getHeight() {
        return height;
    }

    public Ratio getSar() {
        return sar;
    }

    public FrameRate getFrameRate() {
        return frameRate;
    }

    public String getAudioSamplingRate() {
        return audioSamplingRate;
    }

    public String getMimeType() {
        return mimeType;
    }

    public String getSegmentProfiles() {
        return segmentProfiles;
    }

    public String getCodecs() {
        return codecs;
    }

    public Double getMaximumSAPPeriod() {
        return maximumSAPPeriod;
    }

    public Long getStartWithSAP() {
        return startWithSAP;
    }

    public Double getMaxPlayoutRate() {
        return maxPlayoutRate;
    }

    public Boolean getCodingDependency() {
        return codingDependency;
    }

    public VideoScanType getScanType() {
        return scanType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RepresentationBase)) return false;
        RepresentationBase that = (RepresentationBase) o;
        return Objects.equals(framePackings, that.framePackings) &&
                Objects.equals(audioChannelConfigurations, that.audioChannelConfigurations) &&
                Objects.equals(contentProtections, that.contentProtections) &&
                Objects.equals(essentialProperties, that.essentialProperties) &&
                Objects.equals(supplementalProperties, that.supplementalProperties) &&
                Objects.equals(inbandEventStreams, that.inbandEventStreams) &&
                Objects.equals(profiles, that.profiles) &&
                Objects.equals(width, that.width) &&
                Objects.equals(height, that.height) &&
                Objects.equals(sar, that.sar) &&
                Objects.equals(frameRate, that.frameRate) &&
                Objects.equals(audioSamplingRate, that.audioSamplingRate) &&
                Objects.equals(mimeType, that.mimeType) &&
                Objects.equals(segmentProfiles, that.segmentProfiles) &&
                Objects.equals(codecs, that.codecs) &&
                Objects.equals(maximumSAPPeriod, that.maximumSAPPeriod) &&
                Objects.equals(startWithSAP, that.startWithSAP) &&
                Objects.equals(maxPlayoutRate, that.maxPlayoutRate) &&
                Objects.equals(codingDependency, that.codingDependency) &&
                scanType == that.scanType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(framePackings, audioChannelConfigurations, contentProtections, essentialProperties, supplementalProperties, inbandEventStreams, profiles, width, height, sar, frameRate, audioSamplingRate, mimeType, segmentProfiles, codecs, maximumSAPPeriod, startWithSAP, maxPlayoutRate, codingDependency, scanType);
    }

    @Override
    public String toString() {
        return "RepresentationBase{" +
                "framePackings=" + framePackings +
                ", audioChannelConfigurations=" + audioChannelConfigurations +
                ", contentProtections=" + contentProtections +
                ", essentialProperties=" + essentialProperties +
                ", supplementalProperties=" + supplementalProperties +
                ", inbandEventStreams=" + inbandEventStreams +
                ", profiles='" + profiles + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", sar='" + sar + '\'' +
                ", frameRate='" + frameRate + '\'' +
                ", audioSamplingRate='" + audioSamplingRate + '\'' +
                ", mimeType='" + mimeType + '\'' +
                ", segmentProfiles='" + segmentProfiles + '\'' +
                ", codecs='" + codecs + '\'' +
                ", maximumSAPPeriod=" + maximumSAPPeriod +
                ", startWithSAP=" + startWithSAP +
                ", maxPlayoutRate=" + maxPlayoutRate +
                ", codingDependency=" + codingDependency +
                ", scanType=" + scanType +
                '}';
    }

    <T extends AbstractBuilder<T>> T buildUpon(T builder) {
        return builder
                .withFramePackings(framePackings)
                .withAudioChannelConfigurations(audioChannelConfigurations)
                .withContentProtections(contentProtections)
                .withEssentialProperties(essentialProperties)
                .withSupplementalProperties(supplementalProperties)
                .withInbandEventStreams(inbandEventStreams)
                .withProfiles(profiles)
                .withWidth(width)
                .withHeight(height)
                .withSar(sar)
                .withFrameRate(frameRate)
                .withAudioSamplingRate(audioSamplingRate)
                .withMimeType(mimeType)
                .withSegmentProfiles(segmentProfiles)
                .withCodecs(codecs)
                .withMaximumSAPPeriod(maximumSAPPeriod)
                .withStartWithSAP(startWithSAP)
                .withMaxPlayoutRate(maxPlayoutRate)
                .withCodingDependency(codingDependency)
                .withScanType(scanType);
    }

    static abstract class AbstractBuilder<T> {
        List<Descriptor> framePackings;
        List<Descriptor> audioChannelConfigurations;
        List<Descriptor> contentProtections;
        List<Descriptor> essentialProperties;
        List<Descriptor> supplementalProperties;
        List<EventStream> inbandEventStreams;
        String profiles;
        Long width;
        Long height;
        Ratio sar;
        FrameRate frameRate;
        String audioSamplingRate;
        String mimeType;
        String segmentProfiles;
        String codecs;
        Double maximumSAPPeriod;
        Long startWithSAP;
        Double maxPlayoutRate;
        Boolean codingDependency;
        VideoScanType scanType;

        abstract T getThis();

        public T withFramePackings(List<Descriptor> framePackings) {
            this.framePackings = framePackings;
            return getThis();
        }

        public T withAudioChannelConfigurations(List<Descriptor> audioChannelConfigurations) {
            this.audioChannelConfigurations = audioChannelConfigurations;
            return getThis();
        }

        public T withAudioChannelConfigurations(Descriptor audioChannelConfiguration, Descriptor ...moreAudioChannelConfigurations) {
            this.audioChannelConfigurations = Utils.varargsToList(audioChannelConfiguration, moreAudioChannelConfigurations);
            return getThis();
        }

        public T withContentProtections(List<Descriptor> contentProtections) {
            this.contentProtections = contentProtections;
            return getThis();
        }

        public T withEssentialProperties(List<Descriptor> essentialProperties) {
            this.essentialProperties = essentialProperties;
            return getThis();
        }

        public T withSupplementalProperties(List<Descriptor> supplementalProperties) {
            this.supplementalProperties = supplementalProperties;
            return getThis();
        }

        public T withInbandEventStreams(List<EventStream> inbandEventStreams) {
            this.inbandEventStreams = inbandEventStreams;
            return getThis();
        }

        public T withProfiles(String profiles) {
            this.profiles = profiles;
            return getThis();
        }

        public T withWidth(Long width) {
            this.width = width;
            return getThis();
        }

        public T withWidth(int width) {
            this.width = (long) width;
            return getThis();
        }

        public T withHeight(Long height) {
            this.height = height;
            return getThis();
        }

        public T withHeight(int height) {
            this.height = (long) height;
            return getThis();
        }

        public T withSar(Ratio sar) {
            this.sar = sar;
            return getThis();
        }

        public T withFrameRate(FrameRate frameRate) {
            this.frameRate = frameRate;
            return getThis();
        }

        public T withAudioSamplingRate(String audioSamplingRate) {
            this.audioSamplingRate = audioSamplingRate;
            return getThis();
        }

        public T withMimeType(String mimeType) {
            this.mimeType = mimeType;
            return getThis();
        }

        public T withSegmentProfiles(String segmentProfiles) {
            this.segmentProfiles = segmentProfiles;
            return getThis();
        }

        public T withCodecs(String codecs) {
            this.codecs = codecs;
            return getThis();
        }

        public T withMaximumSAPPeriod(Double maximumSAPPeriod) {
            this.maximumSAPPeriod = maximumSAPPeriod;
            return getThis();
        }

        public T withStartWithSAP(Long startWithSAP) {
            this.startWithSAP = startWithSAP;
            return getThis();
        }

        public T withMaxPlayoutRate(Double maxPlayoutRate) {
            this.maxPlayoutRate = maxPlayoutRate;
            return getThis();
        }

        public T withCodingDependency(Boolean codingDependency) {
            this.codingDependency = codingDependency;
            return getThis();
        }

        public T withScanType(VideoScanType scanType) {
            this.scanType = scanType;
            return getThis();
        }
    }
}

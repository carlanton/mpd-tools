package io.lindstrom.mpd.data;

import io.lindstrom.mpd.data.descriptor.Descriptor;
import io.lindstrom.mpd.data.descriptor.Role;
import io.lindstrom.mpd.support.Utils;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@XmlType(propOrder = {
        "id",
        "contentType",
        "mimeType",
        "segmentAlignment",

        "framePackings",
        "audioChannelConfigurations",
        "contentProtections",
        "essentialProperties",
        "supplementalProperties",
        "inbandEventStreams",
        "accessibilities",
        "roles",
        "ratings",
        "viewpoints",
        "contentComponents",
        "baseURLs",
        "segmentBase",
        "segmentList",
        "segmentTemplate",
        "representations"
})
public class AdaptationSet extends RepresentationBase {
    @XmlElement(name = "Accessibility", namespace = MPD.NAMESPACE)
    private final List<Descriptor> accessibilities;

    @XmlElement(name = "Role", namespace = MPD.NAMESPACE)
    private final List<Descriptor> roles;

    @XmlElement(name = "Rating", namespace = MPD.NAMESPACE)
    private final List<Descriptor> ratings;

    @XmlElement(name = "Viewpoint", namespace = MPD.NAMESPACE)
    private final List<Descriptor> viewpoints;

    @XmlElement(name = "ContentComponent", namespace = MPD.NAMESPACE)
    private final List<ContentComponent> contentComponents;

    @XmlElement(name = "BaseURL", namespace = MPD.NAMESPACE)
    private final List<BaseURL> baseURLs;

    @XmlElement(name = "SegmentBase", namespace = MPD.NAMESPACE)
    private final SegmentBase segmentBase;

    @XmlElement(name = "SegmentList", namespace = MPD.NAMESPACE)
    private final SegmentList segmentList;

    @XmlElement(name = "SegmentTemplate", namespace = MPD.NAMESPACE)
    private final SegmentTemplate segmentTemplate;

    @XmlElement(name = "Representation", namespace = MPD.NAMESPACE)
    private final List<Representation> representations;

    @XmlAttribute(name = "href", namespace = "http://www.w3.org/1999/xlink")
    private final String href;

    @XmlAttribute(name = "actuate", namespace = "http://www.w3.org/1999/xlink")
    private final ActuateType actuate;

    @XmlAttribute(name = "id")
    private final Long id;

    @XmlAttribute(name = "group")
    private final Long group;

    @XmlAttribute(name = "lang")
    private final String lang;

    @XmlAttribute(name = "contentType")
    private final String contentType;

    @XmlAttribute(name = "par")
    private final Ratio par;

    @XmlAttribute(name = "minBandwidth")
    private final Long minBandwidth;

    @XmlAttribute(name = "maxBandwidth")
    private final Long maxBandwidth;

    @XmlAttribute(name = "minWidth")
    private final Long minWidth;

    @XmlAttribute(name = "maxWidth")
    private final Long maxWidth;

    @XmlAttribute(name = "minHeight")
    private final Long minHeight;

    @XmlAttribute(name = "maxHeight")
    private final Long maxHeight;

    @XmlAttribute(name = "minFrameRate")
    private final FrameRate minFrameRate;

    @XmlAttribute(name = "maxFrameRate")
    private final FrameRate maxFrameRate;

    @XmlAttribute(name = "segmentAlignment")
    private final String segmentAlignment;

    @XmlAttribute(name = "subsegmentAlignment")
    private final String subsegmentAlignment;

    @XmlAttribute(name = "subsegmentStartsWithSAP")
    private final Long subsegmentStartsWithSAP;

    @XmlAttribute(name = "bitstreamSwitching")
    private final Boolean bitstreamSwitching;

    private AdaptationSet(List<Descriptor> framePackings, List<Descriptor> audioChannelConfigurations, List<Descriptor> contentProtections, List<Descriptor> essentialProperties, List<Descriptor> supplementalProperties, List<EventStream> inbandEventStreams, String profiles, Long width, Long height, Ratio sar, FrameRate frameRate, String audioSamplingRate, String mimeType, String segmentProfiles, String codecs, Double maximumSAPPeriod, Long startWithSAP, Double maxPlayoutRate, Boolean codingDependency, VideoScanType scanType, List<Descriptor> accessibilities, List<Descriptor> roles, List<Descriptor> ratings, List<Descriptor> viewpoints, List<ContentComponent> contentComponents, List<BaseURL> baseURLs, SegmentBase segmentBase, SegmentList segmentList, SegmentTemplate segmentTemplate, List<Representation> representations, String href, ActuateType actuate, Long id, Long group, String lang, String contentType, Ratio par, Long minBandwidth, Long maxBandwidth, Long minWidth, Long maxWidth, Long minHeight, Long maxHeight, FrameRate minFrameRate, FrameRate maxFrameRate, String segmentAlignment, String subsegmentAlignment, Long subsegmentStartsWithSAP, Boolean bitstreamSwitching) {
        super(framePackings, audioChannelConfigurations, contentProtections, essentialProperties, supplementalProperties, inbandEventStreams, profiles, width, height, sar, frameRate, audioSamplingRate, mimeType, segmentProfiles, codecs, maximumSAPPeriod, startWithSAP, maxPlayoutRate, codingDependency, scanType);
        this.accessibilities = accessibilities;
        this.roles = roles;
        this.ratings = ratings;
        this.viewpoints = viewpoints;
        this.contentComponents = contentComponents;
        this.baseURLs = baseURLs;
        this.segmentBase = segmentBase;
        this.segmentList = segmentList;
        this.segmentTemplate = segmentTemplate;
        this.representations = representations;
        this.href = href;
        this.actuate = actuate;
        this.id = id;
        this.group = group;
        this.lang = lang;
        this.contentType = contentType;
        this.par = par;
        this.minBandwidth = minBandwidth;
        this.maxBandwidth = maxBandwidth;
        this.minWidth = minWidth;
        this.maxWidth = maxWidth;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.minFrameRate = minFrameRate;
        this.maxFrameRate = maxFrameRate;
        this.segmentAlignment = segmentAlignment;
        this.subsegmentAlignment = subsegmentAlignment;
        this.subsegmentStartsWithSAP = subsegmentStartsWithSAP;
        this.bitstreamSwitching = bitstreamSwitching;
    }

    @SuppressWarnings("unused")
    private AdaptationSet() {
        this.accessibilities = null;
        this.roles = null;
        this.ratings = null;
        this.viewpoints = null;
        this.contentComponents = null;
        this.baseURLs = null;
        this.segmentBase = null;
        this.segmentList = null;
        this.segmentTemplate = null;
        this.representations = null;
        this.href = null;
        this.actuate = null;
        this.id = null;
        this.group = null;
        this.lang = null;
        this.contentType = null;
        this.par = null;
        this.minBandwidth = null;
        this.maxBandwidth = null;
        this.minWidth = null;
        this.maxWidth = null;
        this.minHeight = null;
        this.maxHeight = null;
        this.minFrameRate = null;
        this.maxFrameRate = null;
        this.segmentAlignment = null;
        this.subsegmentAlignment = null;
        this.subsegmentStartsWithSAP = null;
        this.bitstreamSwitching = null;
    }

    public List<Descriptor> getAccessibilities() {
        return Utils.unmodifiableList(accessibilities);
    }

    public List<Descriptor> getRoles() {
        return Utils.unmodifiableList(roles);
    }

    public List<Descriptor> getRatings() {
        return Utils.unmodifiableList(ratings);
    }

    public List<Descriptor> getViewpoints() {
        return Utils.unmodifiableList(viewpoints);
    }

    public List<ContentComponent> getContentComponents() {
        return Utils.unmodifiableList(contentComponents);
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

    public List<Representation> getRepresentations() {
        return Utils.unmodifiableList(representations);
    }

    public String getHref() {
        return href;
    }

    public ActuateType getActuate() {
        return actuate;
    }

    public Long getId() {
        return id;
    }

    public Long getGroup() {
        return group;
    }

    public String getLang() {
        return lang;
    }

    public String getContentType() {
        return contentType;
    }

    public Ratio getPar() {
        return par;
    }

    public Long getMinBandwidth() {
        return minBandwidth;
    }

    public Long getMaxBandwidth() {
        return maxBandwidth;
    }

    public Long getMinWidth() {
        return minWidth;
    }

    public Long getMaxWidth() {
        return maxWidth;
    }

    public Long getMinHeight() {
        return minHeight;
    }

    public Long getMaxHeight() {
        return maxHeight;
    }

    public FrameRate getMinFrameRate() {
        return minFrameRate;
    }

    public FrameRate getMaxFrameRate() {
        return maxFrameRate;
    }

    public String getSegmentAlignment() {
        return segmentAlignment;
    }

    public String getSubsegmentAlignment() {
        return subsegmentAlignment;
    }

    public Long getSubsegmentStartsWithSAP() {
        return subsegmentStartsWithSAP;
    }

    public Boolean getBitstreamSwitching() {
        return bitstreamSwitching;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AdaptationSet that = (AdaptationSet) o;
        return Objects.equals(accessibilities, that.accessibilities) &&
                Objects.equals(roles, that.roles) &&
                Objects.equals(ratings, that.ratings) &&
                Objects.equals(viewpoints, that.viewpoints) &&
                Objects.equals(contentComponents, that.contentComponents) &&
                Objects.equals(baseURLs, that.baseURLs) &&
                Objects.equals(segmentBase, that.segmentBase) &&
                Objects.equals(segmentList, that.segmentList) &&
                Objects.equals(segmentTemplate, that.segmentTemplate) &&
                Objects.equals(representations, that.representations) &&
                Objects.equals(href, that.href) &&
                actuate == that.actuate &&
                Objects.equals(id, that.id) &&
                Objects.equals(group, that.group) &&
                Objects.equals(lang, that.lang) &&
                Objects.equals(contentType, that.contentType) &&
                Objects.equals(par, that.par) &&
                Objects.equals(minBandwidth, that.minBandwidth) &&
                Objects.equals(maxBandwidth, that.maxBandwidth) &&
                Objects.equals(minWidth, that.minWidth) &&
                Objects.equals(maxWidth, that.maxWidth) &&
                Objects.equals(minHeight, that.minHeight) &&
                Objects.equals(maxHeight, that.maxHeight) &&
                Objects.equals(minFrameRate, that.minFrameRate) &&
                Objects.equals(maxFrameRate, that.maxFrameRate) &&
                Objects.equals(segmentAlignment, that.segmentAlignment) &&
                Objects.equals(subsegmentAlignment, that.subsegmentAlignment) &&
                Objects.equals(subsegmentStartsWithSAP, that.subsegmentStartsWithSAP) &&
                Objects.equals(bitstreamSwitching, that.bitstreamSwitching);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), accessibilities, roles, ratings, viewpoints, contentComponents, baseURLs, segmentBase, segmentList, segmentTemplate, representations, href, actuate, id, group, lang, contentType, par, minBandwidth, maxBandwidth, minWidth, maxWidth, minHeight, maxHeight, minFrameRate, maxFrameRate, segmentAlignment, subsegmentAlignment, subsegmentStartsWithSAP, bitstreamSwitching);
    }

    @Override
    public String toString() {
        return "AdaptationSet{" +
                "super=" + super.toString() +
                ", accessibilities=" + accessibilities +
                ", roles=" + roles +
                ", ratings=" + ratings +
                ", viewpoints=" + viewpoints +
                ", contentComponents=" + contentComponents +
                ", baseURLs=" + baseURLs +
                ", segmentBase=" + segmentBase +
                ", segmentList=" + segmentList +
                ", segmentTemplate=" + segmentTemplate +
                ", representations=" + representations +
                ", href='" + href + '\'' +
                ", actuate=" + actuate +
                ", id=" + id +
                ", group=" + group +
                ", lang='" + lang + '\'' +
                ", contentType='" + contentType + '\'' +
                ", par='" + par + '\'' +
                ", minBandwidth=" + minBandwidth +
                ", maxBandwidth=" + maxBandwidth +
                ", minWidth=" + minWidth +
                ", maxWidth=" + maxWidth +
                ", minHeight=" + minHeight +
                ", maxHeight=" + maxHeight +
                ", minFrameRate='" + minFrameRate + '\'' +
                ", maxFrameRate='" + maxFrameRate + '\'' +
                ", segmentAlignment='" + segmentAlignment + '\'' +
                ", subsegmentAlignment='" + subsegmentAlignment + '\'' +
                ", subsegmentStartsWithSAP=" + subsegmentStartsWithSAP +
                ", bitstreamSwitching=" + bitstreamSwitching +
                '}';
    }

    public Builder buildUpon() {
        return buildUpon(new Builder()
                .withAccessibilities(accessibilities)
                .withRoles(roles)
                .withRatings(ratings)
                .withViewpoints(viewpoints)
                .withContentComponents(contentComponents)
                .withBaseURLs(baseURLs)
                .withSegmentBase(segmentBase)
                .withSegmentList(segmentList)
                .withSegmentTemplate(segmentTemplate)
                .withRepresentations(representations)
                .withHref(href)
                .withActuate(actuate)
                .withId(id)
                .withGroup(group)
                .withLang(lang)
                .withContentType(contentType)
                .withPar(par)
                .withMinBandwidth(minBandwidth)
                .withMaxBandwidth(maxBandwidth)
                .withMinWidth(minWidth)
                .withMaxWidth(maxWidth)
                .withMinHeight(minHeight)
                .withMaxHeight(maxHeight)
                .withMinFrameRate(minFrameRate)
                .withMaxFrameRate(maxFrameRate)
                .withSegmentAlignment(segmentAlignment)
                .withSubsegmentAlignment(subsegmentAlignment)
                .withSubsegmentStartsWithSAP(subsegmentStartsWithSAP)
                .withBitstreamSwitching(bitstreamSwitching));
    }

    public static class Builder extends AbstractBuilder<Builder> {
        private List<Descriptor> accessibilities;
        private List<Descriptor> roles;
        private List<Descriptor> ratings;
        private List<Descriptor> viewpoints;
        private List<ContentComponent> contentComponents;
        private List<BaseURL> baseURLs;
        private SegmentBase segmentBase;
        private SegmentList segmentList;
        private SegmentTemplate segmentTemplate;
        private List<Representation> representations;
        private String href;
        private ActuateType actuate;
        private Long id;
        private Long group;
        private String lang;
        private String contentType;
        private Ratio par;
        private Long minBandwidth;
        private Long maxBandwidth;
        private Long minWidth;
        private Long maxWidth;
        private Long minHeight;
        private Long maxHeight;
        private FrameRate minFrameRate;
        private FrameRate maxFrameRate;
        private String segmentAlignment;
        private String subsegmentAlignment;
        private Long subsegmentStartsWithSAP;
        private Boolean bitstreamSwitching;

        @Override
        Builder getThis() {
            return this;
        }

        public Builder withAccessibilities(List<Descriptor> accessibilities) {
            this.accessibilities = accessibilities;
            return this;
        }

        public Builder withRoles(List<Descriptor> roles) {
            this.roles = roles;
            return this;
        }

        public Builder withRoles(Descriptor role, Descriptor ...moreRoles) {
            this.roles = Utils.varargsToList(role, moreRoles);
            return this;
        }

        public Builder withRoles(Role.Type role, Role.Type ...moreRoles) {
            this.roles = Utils.varargsToList(role, moreRoles).stream()
                    .map(Role::new)
                    .collect(Collectors.toList());
            return this;
        }

        public Builder withRatings(List<Descriptor> ratings) {
            this.ratings = ratings;
            return this;
        }

        public Builder withViewpoints(List<Descriptor> viewpoints) {
            this.viewpoints = viewpoints;
            return this;
        }

        public Builder withContentComponents(List<ContentComponent> contentComponents) {
            this.contentComponents = contentComponents;
            return this;
        }

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

        public Builder withRepresentations(List<Representation> representations) {
            this.representations = representations;
            return this;
        }

        public Builder withRepresentations(Representation representation, Representation ...moreRepresentations) {
            this.representations = Utils.varargsToList(representation, moreRepresentations);
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

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withId(int id) {
            this.id = (long) id;
            return this;
        }

        public Builder withGroup(Long group) {
            this.group = group;
            return this;
        }

        public Builder withLang(String lang) {
            this.lang = lang;
            return this;
        }

        public Builder withContentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder withPar(Ratio par) {
            this.par = par;
            return this;
        }

        public Builder withMinBandwidth(Long minBandwidth) {
            this.minBandwidth = minBandwidth;
            return this;
        }

        public Builder withMaxBandwidth(Long maxBandwidth) {
            this.maxBandwidth = maxBandwidth;
            return this;
        }

        public Builder withMinWidth(Long minWidth) {
            this.minWidth = minWidth;
            return this;
        }

        public Builder withMaxWidth(Long maxWidth) {
            this.maxWidth = maxWidth;
            return this;
        }

        public Builder withMinHeight(Long minHeight) {
            this.minHeight = minHeight;
            return this;
        }

        public Builder withMaxHeight(Long maxHeight) {
            this.maxHeight = maxHeight;
            return this;
        }

        public Builder withMinFrameRate(FrameRate minFrameRate) {
            this.minFrameRate = minFrameRate;
            return this;
        }

        public Builder withMaxFrameRate(FrameRate maxFrameRate) {
            this.maxFrameRate = maxFrameRate;
            return this;
        }

        public Builder withSegmentAlignment(String segmentAlignment) {
            this.segmentAlignment = segmentAlignment;
            return this;
        }

        public Builder withSubsegmentAlignment(String subsegmentAlignment) {
            this.subsegmentAlignment = subsegmentAlignment;
            return this;
        }

        public Builder withSubsegmentStartsWithSAP(Long subsegmentStartsWithSAP) {
            this.subsegmentStartsWithSAP = subsegmentStartsWithSAP;
            return this;
        }

        public Builder withBitstreamSwitching(Boolean bitstreamSwitching) {
            this.bitstreamSwitching = bitstreamSwitching;
            return this;
        }

        public AdaptationSet build() {
            return new AdaptationSet(framePackings, audioChannelConfigurations, contentProtections, essentialProperties, supplementalProperties, inbandEventStreams, profiles, width, height, sar, frameRate, audioSamplingRate, mimeType, segmentProfiles, codecs, maximumSAPPeriod, startWithSAP, maxPlayoutRate, codingDependency, scanType, accessibilities, roles, ratings, viewpoints, contentComponents, baseURLs, segmentBase, segmentList, segmentTemplate, representations, href, actuate, id, group, lang, contentType, par, minBandwidth, maxBandwidth, minWidth, maxWidth, minHeight, maxHeight, minFrameRate, maxFrameRate, segmentAlignment, subsegmentAlignment, subsegmentStartsWithSAP, bitstreamSwitching);
        }
    }
}

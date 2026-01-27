package io.lindstrom.mpd.data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.lindstrom.mpd.data.descriptor.Descriptor;

import java.util.List;
import java.util.Objects;

@JsonPropertyOrder({
        "id",
        "tag",
        "preselectionComponents",
        "lang",
        "order",

        "framePackings",
        "audioChannelConfigurations",
        "contentProtections",
        "essentialProperties",
        "supplementalProperties",
        "inbandEventStreams",
        "labels",

        "accessibilities",
        "roles",
        "ratings",
        "viewpoints"
})
public class Preselection extends RepresentationBase {
    @JacksonXmlProperty(localName = "Accessibility", namespace = MPD.NAMESPACE)
    private final List<Descriptor> accessibilities;

    @JacksonXmlProperty(localName = "Role", namespace = MPD.NAMESPACE)
    private final List<Descriptor> roles;

    @JacksonXmlProperty(localName = "Rating", namespace = MPD.NAMESPACE)
    private final List<Descriptor> ratings;

    @JacksonXmlProperty(localName = "Viewpoint", namespace = MPD.NAMESPACE)
    private final List<Descriptor> viewpoints;

    @JacksonXmlProperty(localName = "Label", namespace = MPD.NAMESPACE)
    private final List<Label> labels;

    @JacksonXmlProperty(isAttribute = true)
    private final String id;

    @JacksonXmlProperty(isAttribute = true)
    private final String tag;

    @JacksonXmlProperty(isAttribute = true)
    private final String preselectionComponents;

    @JacksonXmlProperty(isAttribute = true)
    private final String lang;

    @JacksonXmlProperty(isAttribute = true)
    private final String order;

    private Preselection(List<Descriptor> framePackings, List<Descriptor> audioChannelConfigurations,
                         List<Descriptor> contentProtections, List<Descriptor> essentialProperties,
                         List<Descriptor> supplementalProperties, List<EventStream> inbandEventStreams,
                         String profiles, Long width, Long height, Ratio sar, FrameRate frameRate, String audioSamplingRate,
                         String mimeType, String segmentProfiles, String codecs, Double maximumSAPPeriod, Long startWithSAP,
                         Double maxPlayoutRate, Boolean codingDependency, VideoScanType scanType, List<Label> labels,
                         List<Descriptor> accessibilities, List<Descriptor> roles, List<Descriptor> ratings,
                         List<Descriptor> viewpoints, String id, String tag, String preselectionComponents, String lang, String order) {
        super(framePackings, audioChannelConfigurations, contentProtections, essentialProperties, supplementalProperties,
                inbandEventStreams, profiles, width, height, sar, frameRate, audioSamplingRate, mimeType, segmentProfiles,
                codecs, maximumSAPPeriod, startWithSAP, maxPlayoutRate, codingDependency, scanType);
        this.labels = labels;
        this.accessibilities = accessibilities;
        this.roles = roles;
        this.ratings = ratings;
        this.viewpoints = viewpoints;
        this.id = id;
        this.tag = tag;
        this.preselectionComponents = preselectionComponents;
        this.lang = lang;
        this.order = order;
    }

    private Preselection() {
        super(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        this.labels = null;
        this.accessibilities = null;
        this.roles = null;
        this.ratings = null;
        this.viewpoints = null;
        this.id = null;
        this.tag = null;
        this.preselectionComponents = null;
        this.lang = null;
        this.order = null;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Preselection that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(accessibilities, that.accessibilities) && Objects.equals(roles, that.roles) && Objects.equals(ratings, that.ratings) && Objects.equals(viewpoints, that.viewpoints) && Objects.equals(labels, that.labels) && Objects.equals(id, that.id) && Objects.equals(tag, that.tag) && Objects.equals(preselectionComponents, that.preselectionComponents) && Objects.equals(lang, that.lang) && Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), accessibilities, roles, ratings, viewpoints, labels, id, tag, preselectionComponents, lang, order);
    }

    @Override
    public String toString() {
        return "Preselection{" +
                "accessibilities=" + accessibilities +
                ", roles=" + roles +
                ", ratings=" + ratings +
                ", viewpoints=" + viewpoints +
                ", labels=" + labels +
                ", id='" + id + '\'' +
                ", tag='" + tag + '\'' +
                ", preselectionComponents='" + preselectionComponents + '\'' +
                ", lang='" + lang + '\'' +
                ", order='" + order + '\'' +
                '}';
    }


    public List<Label> getLabels() {
        return labels;
    }

    public List<Descriptor> getAccessibilities() {
        return accessibilities;
    }

    public List<Descriptor> getRoles() {
        return roles;
    }

    public List<Descriptor> getRatings() {
        return ratings;
    }

    public List<Descriptor> getViewpoints() {
        return viewpoints;
    }

    public String getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public String getPreselectionComponents() {
        return preselectionComponents;
    }

    public String getLang() {
        return lang;
    }

    public String getOrder() {
        return order;
    }

    public Builder buildUpon() {
        return buildUpon(new Builder()
                .withAccessibilities(accessibilities)
                .withRoles(roles)
                .withRatings(ratings)
                .withViewpoints(viewpoints)
                .withLabels(labels)
                .withId(id)
                .withTag(tag)
                .withPreselectionComponents(preselectionComponents)
                .withLang(lang)
                .withOrder(order));
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends RepresentationBase.AbstractBuilder<Builder> {
        private List<Descriptor> accessibilities;
        private List<Descriptor> roles;
        private List<Descriptor> ratings;
        private List<Descriptor> viewpoints;
        private List<Label> labels;
        private String id;
        private String tag;
        private String preselectionComponents;
        private String lang;
        private String order;

        private Builder() {
        }

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

        public Builder withRatings(List<Descriptor> ratings) {
            this.ratings = ratings;
            return this;
        }

        public Builder withViewpoints(List<Descriptor> viewpoints) {
            this.viewpoints = viewpoints;
            return this;
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withLabels(List<Label> labels) {
            this.labels = labels;
            return this;
        }

        public Builder withTag(String tag) {
            this.tag = tag;
            return this;
        }

        public Builder withPreselectionComponents(String preselectionComponents) {
            this.preselectionComponents = preselectionComponents;
            return this;
        }

        public Builder withLang(String lang) {
            this.lang = lang;
            return this;
        }

        public Builder withOrder(String order) {
            this.order = order;
            return this;
        }

        public Preselection build() {
            return new Preselection(framePackings, audioChannelConfigurations, contentProtections, essentialProperties,
                    supplementalProperties, inbandEventStreams, profiles, width, height, sar, frameRate, audioSamplingRate,
                    mimeType, segmentProfiles, codecs, maximumSAPPeriod, startWithSAP, maxPlayoutRate, codingDependency,
                    scanType, labels, accessibilities, roles, ratings, viewpoints, id, tag, preselectionComponents, lang, order);
        }
    }
}
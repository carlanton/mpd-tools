package io.lindstrom.mpd.data;

import io.lindstrom.mpd.data.descriptor.Descriptor;
import io.lindstrom.mpd.support.Utils;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;
import java.util.Objects;

@XmlType(propOrder = {
    "accessibility",
    "role",
    "rating",
    "viewpoint",
    "any"
})
public class ContentComponent {
    @XmlElement(name = "Accessibility", namespace = MPD.NAMESPACE)
    private final List<Descriptor> accessibilities;

    @XmlElement(name = "Role", namespace = MPD.NAMESPACE)
    private final List<Descriptor> roles;

    @XmlElement(name = "Rating", namespace = MPD.NAMESPACE)
    private final List<Descriptor> ratings;

    @XmlElement(name = "Viewpoint", namespace = MPD.NAMESPACE)
    private final List<Descriptor> viewpoints;

    @XmlAttribute(name = "id")
    private final Long id;

    @XmlAttribute(name = "lang")
    private final String lang;

    @XmlAttribute(name = "contentType")
    private final String contentType;

    @XmlAttribute(name = "par")
    private final Ratio par;

    private ContentComponent(List<Descriptor> accessibilities, List<Descriptor> roles, List<Descriptor> ratings, List<Descriptor> viewpoints, Long id, String lang, String contentType, Ratio par) {
        this.accessibilities = accessibilities;
        this.roles = roles;
        this.ratings = ratings;
        this.viewpoints = viewpoints;
        this.id = id;
        this.lang = lang;
        this.contentType = contentType;
        this.par = par;
    }

    @SuppressWarnings("unused")
    private ContentComponent() {
        this.accessibilities = null;
        this.roles = null;
        this.ratings = null;
        this.viewpoints = null;
        this.id = null;
        this.lang = null;
        this.contentType = null;
        this.par = null;
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

    public Long getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContentComponent that = (ContentComponent) o;
        return Objects.equals(accessibilities, that.accessibilities) &&
                Objects.equals(roles, that.roles) &&
                Objects.equals(ratings, that.ratings) &&
                Objects.equals(viewpoints, that.viewpoints) &&
                Objects.equals(id, that.id) &&
                Objects.equals(lang, that.lang) &&
                Objects.equals(contentType, that.contentType) &&
                Objects.equals(par, that.par);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessibilities, roles, ratings, viewpoints, id, lang, contentType, par);
    }

    @Override
    public String toString() {
        return "ContentComponent{" +
                "accessibilities=" + accessibilities +
                ", roles=" + roles +
                ", ratings=" + ratings +
                ", viewpoints=" + viewpoints +
                ", id=" + id +
                ", lang='" + lang + '\'' +
                ", contentType='" + contentType + '\'' +
                ", par='" + par + '\'' +
                '}';
    }

    public Builder buildUpon() {
        return new Builder()
                .withAccessibilities(accessibilities)
                .withRoles(roles)
                .withRatings(ratings)
                .withViewpoints(viewpoints)
                .withId(id)
                .withLang(lang)
                .withContentType(contentType)
                .withPar(par);
    }

    public static class Builder {
        private List<Descriptor> accessibilities;
        private List<Descriptor> roles;
        private List<Descriptor> ratings;
        private List<Descriptor> viewpoints;
        private Long id;
        private String lang;
        private String contentType;
        private Ratio par;

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

        public Builder withId(Long id) {
            this.id = id;
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

        public ContentComponent build() {
            return new ContentComponent(accessibilities, roles, ratings, viewpoints, id, lang, contentType, par);
        }
    }
}

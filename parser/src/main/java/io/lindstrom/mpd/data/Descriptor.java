package io.lindstrom.mpd.data;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.Objects;

public class Descriptor {
    @XmlAttribute(name = "schemeIdUri", required = true)
    private final String schemeIdUri;

    @XmlAttribute(name = "value")
    private final String value;

    @XmlAttribute(name = "id")
    private final String id;

    private Descriptor(String schemeIdUri, String value, String id) {
        this.schemeIdUri = schemeIdUri;
        this.value = value;
        this.id = id;
    }

    @SuppressWarnings("unused")
    private Descriptor() {
        this.schemeIdUri = null;
        this.value = null;
        this.id = null;
    }

    public String getSchemeIdUri() {
        return schemeIdUri;
    }

    public String getValue() {
        return value;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Descriptor that = (Descriptor) o;
        return Objects.equals(schemeIdUri, that.schemeIdUri) &&
                Objects.equals(value, that.value) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schemeIdUri, value, id);
    }

    @Override
    public String toString() {
        return "Descriptor{" +
                "schemeIdUri='" + schemeIdUri + '\'' +
                ", value='" + value + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public Builder buildUpon() {
        return new Builder()
                .withSchemeIdUri(schemeIdUri)
                .withValue(value)
                .withId(id);
    }

    public static class Builder {
        private String schemeIdUri;
        private String value;
        private String id;

        public Builder withSchemeIdUri(String schemeIdUri) {
            this.schemeIdUri = schemeIdUri;
            return this;
        }

        public Builder withValue(String value) {
            this.value = value;
            return this;
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Descriptor build() {
            return new Descriptor(schemeIdUri, value, id);
        }
    }
}

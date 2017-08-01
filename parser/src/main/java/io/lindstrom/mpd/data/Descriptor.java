package io.lindstrom.mpd.data;

import javax.xml.bind.annotation.XmlAttribute;

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

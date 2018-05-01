package io.lindstrom.mpd.data.descriptor;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Objects;

public class GenericDescriptor extends Descriptor {
    @JacksonXmlProperty(isAttribute = true, localName = "value")
    private final String value;

    public GenericDescriptor(String schemeIdUri, String value, String id) {
        super(schemeIdUri, id);
        this.value = value;
    }

    public GenericDescriptor(String schemeIdUri, String value) {
        this(schemeIdUri, value, null);
    }

    @SuppressWarnings("unused")
    private GenericDescriptor() {
        super(null, null);
        this.value = null;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GenericDescriptor that = (GenericDescriptor) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value);
    }

    @Override
    public String toString() {
        return "GenericDescriptor{" +
                "value='" + value + '\'' +
                ", schemeIdUri='" + schemeIdUri + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public Builder buildUpon() {
        return new Builder()
                .withId(id)
                .withSchemeIdUri(schemeIdUri)
                .withValue(value);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String value;
        private String schemeIdUri;
        private String id;

        public Builder withValue(String value) {
            this.value = value;
            return this;
        }

        public Builder withSchemeIdUri(String schemeIdUri) {
            this.schemeIdUri = schemeIdUri;
            return this;
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public GenericDescriptor build() {
            return new GenericDescriptor(schemeIdUri, value, id);
        }
    }
}

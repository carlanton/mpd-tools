package io.lindstrom.mpd.data.descriptor.protection;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.lindstrom.mpd.data.descriptor.Descriptor;

import java.util.Objects;

public class ClearKeyContentProtection extends Descriptor {
    public static final String SCHEME_ID_URI = "urn:uuid:e2719d58-a985-b3c9-781a-b030af78d30e";

    @JacksonXmlProperty(isAttribute = true, localName = "value")
    private final String value;

    @JacksonXmlProperty(namespace = "https://dashif.org/guidelines/clearKey")
    private final String laurl;

    public ClearKeyContentProtection(String value, String laurl) {
        super(SCHEME_ID_URI, null);
        this.value = value;
        this.laurl = laurl;
    }

    @SuppressWarnings("unused")
    public ClearKeyContentProtection() {
        super(null, null);
        this.value = null;
        this.laurl = null;
    }

    @Override
    public String getValue() {
        return value;
    }

    public String getLaurl() {
        return laurl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ClearKeyContentProtection that = (ClearKeyContentProtection) o;
        return Objects.equals(value, that.value) && Objects.equals(laurl, that.laurl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value, laurl);
    }

    @Override
    public String toString() {
        return "ClearKeyContentProtection{" +
                "value='" + value + '\'' +
                ", laurl='" + laurl + '\'' +
                ", schemeIdUri='" + schemeIdUri + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public Builder buildUpon() {
        return new Builder()
                .withValue(value)
                .withLaurl(laurl);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String value;
        private String laurl;

        public Builder withValue(String value) {
            this.value = value;
            return this;
        }

        public Builder withLaurl(String laurl) {
            this.laurl = laurl;
            return this;
        }

        public ClearKeyContentProtection build() {
            return new ClearKeyContentProtection(value, laurl);
        }
    }
}
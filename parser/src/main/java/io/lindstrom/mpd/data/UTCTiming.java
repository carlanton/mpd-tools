package io.lindstrom.mpd.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Objects;

public class UTCTiming {
    @JacksonXmlProperty(isAttribute = true)
    private final Type schemeIdUri;

    @JacksonXmlProperty(isAttribute = true, localName = "value")
    private final String value;

    @JacksonXmlProperty(isAttribute = true)
    private final String id;

    private UTCTiming(Type schemeIdUri, String value, String id) {
        this.schemeIdUri = schemeIdUri;
        this.value = value;
        this.id = id;
    }

    private UTCTiming() {
        this.schemeIdUri = null;
        this.value = null;
        this.id = null;
    }

    public Type getSchemeIdUri() {
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
        UTCTiming utcTiming = (UTCTiming) o;
        return schemeIdUri == utcTiming.schemeIdUri &&
                Objects.equals(value, utcTiming.value) &&
                Objects.equals(id, utcTiming.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schemeIdUri, value, id);
    }

    @Override
    public String toString() {
        return "UTCTiming{" +
                "schemeIdUri=" + schemeIdUri +
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
        private Type schemeIdUri;
        private String value;
        private String id;

        public Builder withSchemeIdUri(Type schemeIdUri) {
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

        public UTCTiming build() {
            return new UTCTiming(schemeIdUri, value, id);
        }
    }

    public enum Type {
        @JsonProperty("urn:mpeg:dash:utc:ntp:2014") NTP,
        @JsonProperty("urn:mpeg:dash:utc:sntp:2014") SNTP,
        @JsonProperty("urn:mpeg:dash:utc:http-head:2014") HTTP_HEAD,
        @JsonProperty("urn:mpeg:dash:utc:http-xsdate:2014") HTTP_XSDATE,
        @JsonProperty("urn:mpeg:dash:utc:http-iso:2014") HTTP_ISO,
        @JsonProperty("urn:mpeg:dash:utc:http-ntp:2014") HTTP_NTP,
        @JsonProperty("urn:mpeg:dash:utc:direct:2014") DIRECT;
    }
}

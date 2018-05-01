package io.lindstrom.mpd.data.descriptor.protection;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.lindstrom.mpd.data.descriptor.Descriptor;

import java.util.Objects;

public class Mp4Protection extends Descriptor {
    public static final String SCHEME_ID_URI = "urn:mpeg:dash:mp4protection:2011";

    @JacksonXmlProperty(isAttribute = true, localName = "default_KID", namespace = "urn:mpeg:cenc:2013")
    private final String defaultKID;

    @JacksonXmlProperty(isAttribute = true, localName = "value")
    private final String value;

    public Mp4Protection(String value, String defaultKID) {
        super(SCHEME_ID_URI, null);
        this.value = value;
        this.defaultKID = defaultKID;
    }

    @SuppressWarnings("unused")
    private Mp4Protection() {
        super(null, null);
        this.defaultKID = null;
        this.value = null;
    }

    @Override
    public String getValue() {
        return value;
    }

    public String getDefaultKID() {
        return defaultKID;
    }

    @Override
    public String toString() {
        return "Mp4Protection{" +
                "defaultKID='" + defaultKID + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Mp4Protection that = (Mp4Protection) o;
        return Objects.equals(defaultKID, that.defaultKID) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), defaultKID, value);
    }
}

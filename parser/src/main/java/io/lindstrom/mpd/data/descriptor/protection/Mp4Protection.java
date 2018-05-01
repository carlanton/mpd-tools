package io.lindstrom.mpd.data.descriptor.protection;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.lindstrom.mpd.data.descriptor.Descriptor;

/*
			<ContentProtection
			    schemeIdUri="urn:mpeg:dash:mp4protection:2011"
			    value="cenc" cenc:default_KID="9eb4050d-e44b-4802-932e-27d75083e266" />
 */

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
        return null;
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
}

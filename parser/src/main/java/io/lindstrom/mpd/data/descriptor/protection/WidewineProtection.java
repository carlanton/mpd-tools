package io.lindstrom.mpd.data.descriptor.protection;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.lindstrom.mpd.data.descriptor.Descriptor;

public class WidewineProtection extends Descriptor {
    public static final String SCHEME_ID_URI = "urn:uuid:edef8ba9-79d6-4ace-a3c8-27dcd51d21ed";

    @JacksonXmlProperty(namespace = "urn:mpeg:cenc:2013")
    private final String pssh;

    @JacksonXmlProperty(isAttribute = true, localName = "value")
    private final String value;

    public WidewineProtection(String value, String pssh) {
        super(SCHEME_ID_URI, null);
        this.value = value;
        this.pssh = pssh;
    }

    @SuppressWarnings("unused")
    private WidewineProtection() {
        super(null, null);
        this.pssh = null;
        this.value = null;
    }

    @Override
    public String getValue() {
        return null;
    }

    public String getPssh() {
        return pssh;
    }

    @Override
    public String toString() {
        return "WidewineProtection{" +
                "pssh='" + pssh + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}

package io.lindstrom.mpd.data.descriptor.protection;

import io.lindstrom.mpd.data.descriptor.Descriptor;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/*
			<ContentProtection value="Widevine" schemeIdUri="urn:uuid:edef8ba9-79d6-4ace-a3c8-27dcd51d21ed">
				<cenc:pssh>AAAANHBzc2gAAAAA7e+LqXnWSs6jyCfc1R0h7QAAABQIARIQnrQFDeRLSAKTLifXUIPiZg==</cenc:pssh>
			</ContentProtection> */

public class WidewineProtection extends Descriptor {
    public static final String SCHEME_ID_URI = "urn:uuid:edef8ba9-79d6-4ace-a3c8-27dcd51d21ed";

    @XmlElement(name = "pssh", namespace = "urn:mpeg:cenc:2013")
    private final String pssh;

    @XmlAttribute(name = "value")
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

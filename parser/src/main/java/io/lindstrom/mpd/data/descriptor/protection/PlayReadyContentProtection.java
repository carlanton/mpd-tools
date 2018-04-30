package io.lindstrom.mpd.data.descriptor.protection;

import io.lindstrom.mpd.data.descriptor.GenericDescriptor;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/*
<ContentProtection value="MSPR 2.0" schemeIdUri="urn:uuid:9a04f079-9840-4286-ab92-e65be0885f95">
    <cenc:pssh>AAAB5HBzc2gAAAAAmgTweZhAQoarkuZb4IhflQAAAcTEAQAAAQABALoBPABXAFIATQBIAEUAQQBEAEUAUgAgAHgAbQBsAG4AcwA9ACIAaAB0AHQAcAA6AC8ALwBzAGMAaABlAG0AYQBzAC4AbQBpAGMAcgBvAHMAbwBmAHQALgBjAG8AbQAvAEQAUgBNAC8AMgAwADAANwAvADAAMwAvAFAAbABhAHkAUgBlAGEAZAB5AEgAZQBhAGQAZQByACIAIAB2AGUAcgBzAGkAbwBuAD0AIgA0AC4AMAAuADAALgAwACIAPgA8AEQAQQBUAEEAPgA8AFAAUgBPAFQARQBDAFQASQBOAEYATwA+ADwASwBFAFkATABFAE4APgAxADYAPAAvAEsARQBZAEwARQBOAD4APABBAEwARwBJAEQAPgBBAEUAUwBDAFQAUgA8AC8AQQBMAEcASQBEAD4APAAvAFAAUgBPAFQARQBDAFQASQBOAEYATwA+ADwASwBJAEQAPgBEAFEAVwAwAG4AawB2AGsAQQBrAGkAVABMAGkAZgBYAFUASQBQAGkAWgBnAD0APQA8AC8ASwBJAEQAPgA8AC8ARABBAFQAQQA+ADwALwBXAFIATQBIAEUAQQBEAEUAUgA+AA==</cenc:pssh>
    <pro xmlns="urn:microsoft:playready">xAEAAAEAAQC6ATwAVwBSAE0ASABFAEEARABFAFIAIAB4AG0AbABuAHMAPQAiAGgAdAB0AHAAOgAvAC8AcwBjAGgAZQBtAGEAcwAuAG0AaQBjAHIAbwBzAG8AZgB0AC4AYwBvAG0ALwBEAFIATQAvADIAMAAwADcALwAwADMALwBQAGwAYQB5AFIAZQBhAGQAeQBIAGUAYQBkAGUAcgAiACAAdgBlAHIAcwBpAG8AbgA9ACIANAAuADAALgAwAC4AMAAiAD4APABEAEEAVABBAD4APABQAFIATwBUAEUAQwBUAEkATgBGAE8APgA8AEsARQBZAEwARQBOAD4AMQA2ADwALwBLAEUAWQBMAEUATgA+ADwAQQBMAEcASQBEAD4AQQBFAFMAQwBUAFIAPAAvAEEATABHAEkARAA+ADwALwBQAFIATwBUAEUAQwBUAEkATgBGAE8APgA8AEsASQBEAD4ARABRAFcAMABuAGsAdgBrAEEAawBpAFQATABpAGYAWABVAEkAUABpAFoAZwA9AD0APAAvAEsASQBEAD4APAAvAEQAQQBUAEEAPgA8AC8AVwBSAE0ASABFAEEARABFAFIAPgA=</pro>
</ContentProtection>
 */

public class PlayReadyContentProtection extends GenericDescriptor {
    public static final String SCHEME_ID_URI = "urn:uuid:9a04f079-9840-4286-ab92-e65be0885f95";

    @XmlAttribute(name = "default_KID", namespace = "urn:mpeg:cenc:2013")
    private final String defaultKID;

    @XmlElement(name = "pssh", namespace = "urn:mpeg:cenc:2013")
    private final String pssh;

    @XmlElement(name = "pro", namespace = "urn:microsoft:playready")
    private final String pro;

    public PlayReadyContentProtection(String value, String defaultKID, String pssh, String pro) {
        super(SCHEME_ID_URI, value);
        this.defaultKID = defaultKID;
        this.pssh = pssh;
        this.pro = pro;
    }

    private PlayReadyContentProtection() {
        super(null, null);
        this.defaultKID = null;
        this.pssh = null;
        this.pro = null;
    }
}

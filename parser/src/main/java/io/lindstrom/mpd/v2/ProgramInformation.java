package io.lindstrom.mpd.v2;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableProgramInformation.class)
@JsonDeserialize(as = ImmutableProgramInformation.class)
public interface ProgramInformation {
    @JacksonXmlProperty(isAttribute = true)
    String lang();

    @JacksonXmlProperty(isAttribute = true)
    String moreInformationURL();

    @JacksonXmlProperty(localName = "Title", namespace = MPD.NAMESPACE)
    String title();

    @JacksonXmlProperty(localName = "Source", namespace = MPD.NAMESPACE)
    String source();

    @JacksonXmlProperty(localName = "Copyright", namespace = MPD.NAMESPACE)
    String copyright();


    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableProgramInformation.Builder {}

}
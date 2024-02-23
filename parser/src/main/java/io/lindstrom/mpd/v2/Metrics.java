package io.lindstrom.mpd.v2;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.lindstrom.mpd.v2.descriptor.Descriptor;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableMetrics.class)
@JsonDeserialize(as = ImmutableMetrics.class)
public interface Metrics {
    @JacksonXmlProperty(localName = "Reporting", namespace = MPD.NAMESPACE)
    List<Descriptor> reportings();

    @JacksonXmlProperty(localName = "Range", namespace = MPD.NAMESPACE)
    List<Range> ranges();

    @JacksonXmlProperty(isAttribute = true)
    String metrics();


    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableMetrics.Builder {}
}
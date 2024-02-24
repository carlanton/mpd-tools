package io.lindstrom.mpd.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@Value.Immutable
@JsonSerialize(as = ImmutableUTCTiming.class)
@JsonDeserialize(as = ImmutableUTCTiming.class)
public interface UTCTiming {
    @JacksonXmlProperty(isAttribute = true)
    Type schemeIdUri();

    @JacksonXmlProperty(isAttribute = true, localName = "value")
    @Nullable
    String value();

    @JacksonXmlProperty(isAttribute = true)
    @Nullable
    String id();

    default Builder buildUpon() {
        return builder().from(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableUTCTiming.Builder {}

    enum Type {
        @JsonProperty("urn:mpeg:dash:utc:ntp:2014") NTP,
        @JsonProperty("urn:mpeg:dash:utc:sntp:2014") SNTP,
        @JsonProperty("urn:mpeg:dash:utc:http-head:2014") HTTP_HEAD,
        @JsonProperty("urn:mpeg:dash:utc:http-xsdate:2014") HTTP_XSDATE,
        @JsonProperty("urn:mpeg:dash:utc:http-iso:2014") HTTP_ISO,
        @JsonProperty("urn:mpeg:dash:utc:http-ntp:2014") HTTP_NTP,
        @JsonProperty("urn:mpeg:dash:utc:direct:2014") DIRECT;
    }
}
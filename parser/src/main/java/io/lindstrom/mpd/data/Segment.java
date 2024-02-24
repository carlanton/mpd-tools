package io.lindstrom.mpd.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableSegment.class)
@JsonDeserialize(as = ImmutableSegment.class)
public interface Segment {
    @JacksonXmlProperty(isAttribute = true)
    Long t();

    @JacksonXmlProperty(isAttribute = true)
    Long n();

    @JacksonXmlProperty(isAttribute = true)
    long d();

    @JacksonXmlProperty(isAttribute = true)
    Long r();

    static Segment of(long t, long d, long r) {
        return Segment.builder().t(t).d(d).r(r).build();
    }

    static Segment of(long t, long d) {
        return Segment.builder().t(t).d(d).build();
    }

    default Builder buildUpon() {
        return builder().from(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableSegment.Builder {}
}
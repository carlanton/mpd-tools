package io.lindstrom.mpd.data.scte35;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.Objects;

/**
 * Represents a SCTE-35 SpliceTime element within a Program.
 * This element contains the pts time for the splice event.
 * 
 * References:
 * - SCTE-35 spec
 * - DASH-IF IOP section 5.5.2 (Opportunity Signalling via SCTE-35)
 */
@JacksonXmlRootElement(localName = "SpliceTime", namespace = "urn:scte:scte35:2013:xml")
public class Scte35SpliceTime {
    @JacksonXmlProperty(isAttribute = true)
    private final Long ptsTime;
    
    private Scte35SpliceTime(Long ptsTime) {
        this.ptsTime = ptsTime;
    }

    @SuppressWarnings("unused")
    private Scte35SpliceTime() {
        this.ptsTime = null;
    }
    
    public Long getPtsTime() {
        return ptsTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scte35SpliceTime that = (Scte35SpliceTime) o;
        return Objects.equals(ptsTime, that.ptsTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ptsTime);
    }

    @Override
    public String toString() {
        return "Scte35SpliceTime{" +
                "ptsTime=" + ptsTime +
                '}';
    }

    public Builder buildUpon() {
        return new Builder()
                .withPtsTime(ptsTime);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long ptsTime;

        public Builder withPtsTime(Long ptsTime) {
            this.ptsTime = ptsTime;
            return this;
        }

        public Scte35SpliceTime build() {
            return new Scte35SpliceTime(ptsTime);
        }
    }
} 
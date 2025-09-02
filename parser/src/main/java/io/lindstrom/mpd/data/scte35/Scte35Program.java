package io.lindstrom.mpd.data.scte35;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.Objects;

/**
 * Represents a SCTE-35 Program element within a SpliceInsert.
 * This contains program-specific splice information.
 * 
 * References:
 * - SCTE-35 spec
 * - DASH-IF IOP section 5.5.2 (Opportunity Signalling via SCTE-35)
 */
@JacksonXmlRootElement(localName = "Program", namespace = "urn:scte:scte35:2013:xml")
public class Scte35Program {
    
    @JacksonXmlProperty(localName = "SpliceTime", namespace = "urn:scte:scte35:2013:xml")
    private final Scte35SpliceTime spliceTime;
    
    private Scte35Program(Scte35SpliceTime spliceTime) {
        this.spliceTime = spliceTime;
    }

    @SuppressWarnings("unused")
    private Scte35Program() {
        this.spliceTime = null;
    }
    
    public Scte35SpliceTime getSpliceTime() {
        return spliceTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scte35Program that = (Scte35Program) o;
        return Objects.equals(spliceTime, that.spliceTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spliceTime);
    }

    @Override
    public String toString() {
        return "Scte35Program{" +
                "spliceTime=" + spliceTime +
                '}';
    }

    public Builder buildUpon() {
        return new Builder()
                .withSpliceTime(spliceTime);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Scte35SpliceTime spliceTime;

        public Builder withSpliceTime(Scte35SpliceTime spliceTime) {
            this.spliceTime = spliceTime;
            return this;
        }

        public Scte35Program build() {
            return new Scte35Program(spliceTime);
        }
    }
} 
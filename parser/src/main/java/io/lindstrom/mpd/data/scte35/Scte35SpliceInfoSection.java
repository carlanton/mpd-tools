package io.lindstrom.mpd.data.scte35;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.Objects;

/**
 * Represents a SCTE-35 SpliceInfoSection element.
 * This is the main container for SCTE-35 messages in the 2013:xml format.
 * 
 * References:
 * - SCTE-35 spec
 * - DASH-IF IOP section 5.5.2 (Opportunity Signalling via SCTE-35)
 */
@JacksonXmlRootElement(localName = "SpliceInfoSection", namespace = "urn:scte:scte35:2013:xml")
public class Scte35SpliceInfoSection {
    @JacksonXmlProperty(isAttribute = true)
    private final String protocolVersion;

    @JacksonXmlProperty(isAttribute = true)
    private final Long ptsAdjustment;

    @JacksonXmlProperty(isAttribute = true)
    private final Integer tier;
    
    @JacksonXmlProperty(localName = "SpliceInsert", namespace = "urn:scte:scte35:2013:xml")
    private final Scte35SpliceInsert spliceInsert;

    private Scte35SpliceInfoSection(String protocolVersion, Long ptsAdjustment, Integer tier, 
                                    Scte35SpliceInsert spliceInsert) {
        this.protocolVersion = protocolVersion;
        this.ptsAdjustment = ptsAdjustment;
        this.tier = tier;
        this.spliceInsert = spliceInsert;
    }

    @SuppressWarnings("unused")
    private Scte35SpliceInfoSection() {
        this.protocolVersion = null;
        this.ptsAdjustment = null;
        this.tier = null;
        this.spliceInsert = null;
    }

    public String getProtocolVersion() {
        return protocolVersion;
    }

    public Long getPtsAdjustment() {
        return ptsAdjustment;
    }

    public Integer getTier() {
        return tier;
    }
    
    public Scte35SpliceInsert getSpliceInsert() {
        return spliceInsert;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scte35SpliceInfoSection that = (Scte35SpliceInfoSection) o;
        return Objects.equals(protocolVersion, that.protocolVersion) &&
                Objects.equals(ptsAdjustment, that.ptsAdjustment) &&
                Objects.equals(tier, that.tier) &&
                Objects.equals(spliceInsert, that.spliceInsert);
    }

    @Override
    public int hashCode() {
        return Objects.hash(protocolVersion, ptsAdjustment, tier, spliceInsert);
    }

    @Override
    public String toString() {
        return "Scte35SpliceInfoSection{" +
                "protocolVersion='" + protocolVersion + '\'' +
                ", ptsAdjustment=" + ptsAdjustment +
                ", tier=" + tier +
                ", spliceInsert=" + spliceInsert +
                '}';
    }

    public Builder buildUpon() {
        return new Builder()
                .withProtocolVersion(protocolVersion)
                .withPtsAdjustment(ptsAdjustment)
                .withTier(tier)
                .withSpliceInsert(spliceInsert);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String protocolVersion;
        private Long ptsAdjustment;
        private Integer tier;
        private Scte35SpliceInsert spliceInsert;

        public Builder withProtocolVersion(String protocolVersion) {
            this.protocolVersion = protocolVersion;
            return this;
        }

        public Builder withPtsAdjustment(Long ptsAdjustment) {
            this.ptsAdjustment = ptsAdjustment;
            return this;
        }

        public Builder withTier(Integer tier) {
            this.tier = tier;
            return this;
        }
        
        public Builder withSpliceInsert(Scte35SpliceInsert spliceInsert) {
            this.spliceInsert = spliceInsert;
            return this;
        }

        public Scte35SpliceInfoSection build() {
            return new Scte35SpliceInfoSection(protocolVersion, ptsAdjustment, tier, 
                                              spliceInsert);
        }
    }
} 
package io.lindstrom.mpd.data.scte35;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.Objects;

/**
 * Represents a SCTE-35 SpliceInsert element.
 * The splice_insert() command is used to mark where a program may be interrupted.
 * 
 * References:
 * - SCTE-35 spec section 9.3.3 (splice_insert())
 * - DASH-IF IOP section 5.5.2 (Opportunity Signalling via SCTE-35)
 */
@JacksonXmlRootElement(localName = "SpliceInsert", namespace = "urn:scte:scte35:2013:xml")
public class Scte35SpliceInsert {
    @JacksonXmlProperty(isAttribute = true)
    private final Long spliceEventId;

    @JacksonXmlProperty(isAttribute = true)
    private final Boolean spliceEventCancelIndicator;

    @JacksonXmlProperty(isAttribute = true)
    private final Boolean outOfNetworkIndicator;

    @JacksonXmlProperty(isAttribute = true)
    private final Boolean spliceImmediateFlag;

    @JacksonXmlProperty(isAttribute = true)
    private final Integer uniqueProgramId;

    @JacksonXmlProperty(isAttribute = true)
    private final Integer availNum;

    @JacksonXmlProperty(isAttribute = true)
    private final Integer availsExpected;
    
    @JacksonXmlProperty(localName = "Program", namespace = "urn:scte:scte35:2013:xml")
    private final Scte35Program program;

    private Scte35SpliceInsert(Long spliceEventId, Boolean spliceEventCancelIndicator, 
                              Boolean outOfNetworkIndicator, Boolean spliceImmediateFlag,
                              Integer uniqueProgramId, Integer availNum, Integer availsExpected,
                              Scte35Program program) {
        this.spliceEventId = spliceEventId;
        this.spliceEventCancelIndicator = spliceEventCancelIndicator;
        this.outOfNetworkIndicator = outOfNetworkIndicator;
        this.spliceImmediateFlag = spliceImmediateFlag;
        this.uniqueProgramId = uniqueProgramId;
        this.availNum = availNum;
        this.availsExpected = availsExpected;
        this.program = program;
    }

    @SuppressWarnings("unused")
    private Scte35SpliceInsert() {
        this.spliceEventId = null;
        this.spliceEventCancelIndicator = null;
        this.outOfNetworkIndicator = null;
        this.spliceImmediateFlag = null;
        this.uniqueProgramId = null;
        this.availNum = null;
        this.availsExpected = null;
        this.program = null;
    }

    public Long getSpliceEventId() {
        return spliceEventId;
    }

    public Boolean getSpliceEventCancelIndicator() {
        return spliceEventCancelIndicator;
    }

    public Boolean getOutOfNetworkIndicator() {
        return outOfNetworkIndicator;
    }

    public Boolean getSpliceImmediateFlag() {
        return spliceImmediateFlag;
    }

    public Integer getUniqueProgramId() {
        return uniqueProgramId;
    }

    public Integer getAvailNum() {
        return availNum;
    }

    public Integer getAvailsExpected() {
        return availsExpected;
    }
    
    public Scte35Program getProgram() {
        return program;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scte35SpliceInsert that = (Scte35SpliceInsert) o;
        return Objects.equals(spliceEventId, that.spliceEventId) &&
                Objects.equals(spliceEventCancelIndicator, that.spliceEventCancelIndicator) &&
                Objects.equals(outOfNetworkIndicator, that.outOfNetworkIndicator) &&
                Objects.equals(spliceImmediateFlag, that.spliceImmediateFlag) &&
                Objects.equals(uniqueProgramId, that.uniqueProgramId) &&
                Objects.equals(availNum, that.availNum) &&
                Objects.equals(availsExpected, that.availsExpected) &&
                Objects.equals(program, that.program);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spliceEventId, spliceEventCancelIndicator, outOfNetworkIndicator,
                           spliceImmediateFlag, uniqueProgramId, availNum, availsExpected,
                           program);
    }

    @Override
    public String toString() {
        return "Scte35SpliceInsert{" +
                "spliceEventId=" + spliceEventId +
                ", spliceEventCancelIndicator=" + spliceEventCancelIndicator +
                ", outOfNetworkIndicator=" + outOfNetworkIndicator +
                ", spliceImmediateFlag=" + spliceImmediateFlag +
                ", uniqueProgramId=" + uniqueProgramId +
                ", availNum=" + availNum +
                ", availsExpected=" + availsExpected +
                ", program=" + program +
                '}';
    }

    public Builder buildUpon() {
        return new Builder()
                .withSpliceEventId(spliceEventId)
                .withSpliceEventCancelIndicator(spliceEventCancelIndicator)
                .withOutOfNetworkIndicator(outOfNetworkIndicator)
                .withSpliceImmediateFlag(spliceImmediateFlag)
                .withUniqueProgramId(uniqueProgramId)
                .withAvailNum(availNum)
                .withAvailsExpected(availsExpected)
                .withProgram(program);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long spliceEventId;
        private Boolean spliceEventCancelIndicator;
        private Boolean outOfNetworkIndicator;
        private Boolean spliceImmediateFlag;
        private Integer uniqueProgramId;
        private Integer availNum;
        private Integer availsExpected;
        private Scte35Program program;

        public Builder withSpliceEventId(Long spliceEventId) {
            this.spliceEventId = spliceEventId;
            return this;
        }

        public Builder withSpliceEventCancelIndicator(Boolean spliceEventCancelIndicator) {
            this.spliceEventCancelIndicator = spliceEventCancelIndicator;
            return this;
        }

        public Builder withOutOfNetworkIndicator(Boolean outOfNetworkIndicator) {
            this.outOfNetworkIndicator = outOfNetworkIndicator;
            return this;
        }

        public Builder withSpliceImmediateFlag(Boolean spliceImmediateFlag) {
            this.spliceImmediateFlag = spliceImmediateFlag;
            return this;
        }

        public Builder withUniqueProgramId(Integer uniqueProgramId) {
            this.uniqueProgramId = uniqueProgramId;
            return this;
        }

        public Builder withAvailNum(Integer availNum) {
            this.availNum = availNum;
            return this;
        }

        public Builder withAvailsExpected(Integer availsExpected) {
            this.availsExpected = availsExpected;
            return this;
        }

        public Builder withProgram(Scte35Program program) {
            this.program = program;
            return this;
        }

        public Scte35SpliceInsert build() {
            return new Scte35SpliceInsert(spliceEventId, spliceEventCancelIndicator,
                                         outOfNetworkIndicator, spliceImmediateFlag,
                                         uniqueProgramId, availNum, availsExpected,
                                         program);
        }
    }
} 
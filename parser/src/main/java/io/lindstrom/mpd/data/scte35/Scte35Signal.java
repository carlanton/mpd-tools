package io.lindstrom.mpd.data.scte35;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.Objects;

/**
 * Represents a SCTE-35 Signal element according to SCTE-214 specification.
 */
@JacksonXmlRootElement(localName = "Signal", namespace = "urn:scte:scte35:2014:xml+bin")
public class Scte35Signal {

    @JacksonXmlProperty(localName = "Binary", namespace = "urn:scte:scte35:2014:xml+bin")
    private final String binary;

    private Scte35Signal(String binary) {
        this.binary = binary;
    }

    @SuppressWarnings("unused")
    private Scte35Signal() {
        this.binary = null;
    }

    public String getBinary() {
        return binary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scte35Signal that = (Scte35Signal) o;
        return Objects.equals(binary, that.binary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(binary);
    }

    @Override
    public String toString() {
        return "Scte35Signal{" +
                "binary='" + binary + "'" +
                '}';
    }

    public Builder buildUpon() {
        return new Builder()
                .withBinary(binary);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String binary;

        public Builder withBinary(String binary) {
            this.binary = binary;
            return this;
        }

        public Scte35Signal build() {
            return new Scte35Signal(binary);
        }
    }
}

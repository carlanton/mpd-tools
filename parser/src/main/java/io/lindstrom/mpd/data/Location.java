package io.lindstrom.mpd.data;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import java.util.Objects;

public class Location {
    @JacksonXmlText
    private final String value;

    @JacksonXmlProperty(isAttribute = true)
    private final String serviceLocation;

    @SuppressWarnings("unused")
    private Location() {
        this.value = null;
        this.serviceLocation = null;
    }

    @SuppressWarnings("unused")
    private Location(String value) {
        this.value = value;
        this.serviceLocation = null;
    }

    private Location(String value, String serviceLocation) {
        this.value = value;
        this.serviceLocation = serviceLocation;
    }

    public String getValue() {
        return value;
    }

    public String getServiceLocation() {
        return serviceLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Location location)) return false;
        return Objects.equals(value, location.value) && Objects.equals(serviceLocation, location.serviceLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, serviceLocation);
    }

    @Override
    public String toString() {
        return "Location{" +
                "value='" + value + '\'' +
                ", serviceLocation='" + serviceLocation + '\'' +
                '}';
    }

    public Builder buildUpon() {
        return new Builder()
                .withValue(value)
                .withServiceLocation(serviceLocation);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String value;
        private String serviceLocation;

        public Builder withValue(String value) {
            this.value = value;
            return this;
        }

        public Builder withServiceLocation(String serviceLocation) {
            this.serviceLocation = serviceLocation;
            return this;
        }

        public Location build() {
            return new Location(value, serviceLocation);
        }
    }
}
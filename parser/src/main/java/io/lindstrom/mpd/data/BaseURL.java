package io.lindstrom.mpd.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;
import java.util.Objects;

public class BaseURL {
    @XmlValue
    private final String value;

    @XmlAttribute(name = "serviceLocation")
    private final String serviceLocation;

    @XmlAttribute(name = "byteRange")
    private final String byteRange;

    @XmlAttribute(name = "availabilityTimeOffset")
    private final Double availabilityTimeOffset;

    @XmlAttribute(name = "availabilityTimeComplete")
    private final Boolean availabilityTimeComplete;

    @SuppressWarnings("unused")
    private BaseURL() {
        this.value = null;
        this.serviceLocation = null;
        this.byteRange = null;
        this.availabilityTimeOffset = null;
        this.availabilityTimeComplete = null;
    }

    @SuppressWarnings("unused")
    private BaseURL(String value) {
        this.value = value;
        this.serviceLocation = null;
        this.byteRange = null;
        this.availabilityTimeOffset = null;
        this.availabilityTimeComplete = null;
    }

    private BaseURL(String value, String serviceLocation, String byteRange, Double availabilityTimeOffset, Boolean availabilityTimeComplete) {
        this.value = value;
        this.serviceLocation = serviceLocation;
        this.byteRange = byteRange;
        this.availabilityTimeOffset = availabilityTimeOffset;
        this.availabilityTimeComplete = availabilityTimeComplete;
    }

    public String getValue() {
        return value;
    }

    public String getServiceLocation() {
        return serviceLocation;
    }

    public String getByteRange() {
        return byteRange;
    }

    public Double getAvailabilityTimeOffset() {
        return availabilityTimeOffset;
    }

    public Boolean getAvailabilityTimeComplete() {
        return availabilityTimeComplete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseURL baseURL = (BaseURL) o;
        return Objects.equals(value, baseURL.value) &&
                Objects.equals(serviceLocation, baseURL.serviceLocation) &&
                Objects.equals(byteRange, baseURL.byteRange) &&
                Objects.equals(availabilityTimeOffset, baseURL.availabilityTimeOffset) &&
                Objects.equals(availabilityTimeComplete, baseURL.availabilityTimeComplete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, serviceLocation, byteRange, availabilityTimeOffset, availabilityTimeComplete);
    }

    @Override
    public String toString() {
        return "BaseURL{" +
                "value='" + value + '\'' +
                ", serviceLocation='" + serviceLocation + '\'' +
                ", byteRange='" + byteRange + '\'' +
                ", availabilityTimeOffset=" + availabilityTimeOffset +
                ", availabilityTimeComplete=" + availabilityTimeComplete +
                '}';
    }

    public Builder buildUpon() {
        return new Builder()
                .withValue(value)
                .withServiceLocation(serviceLocation)
                .withByteRange(byteRange)
                .withAvailabilityTimeOffset(availabilityTimeOffset)
                .withAvailabilityTimeComplete(availabilityTimeComplete);
    }

    public static class Builder {
        private String value;
        private String serviceLocation;
        private String byteRange;
        private Double availabilityTimeOffset;
        private Boolean availabilityTimeComplete;

        public Builder withValue(String value) {
            this.value = value;
            return this;
        }

        public Builder withServiceLocation(String serviceLocation) {
            this.serviceLocation = serviceLocation;
            return this;
        }

        public Builder withByteRange(String byteRange) {
            this.byteRange = byteRange;
            return this;
        }

        public Builder withAvailabilityTimeOffset(Double availabilityTimeOffset) {
            this.availabilityTimeOffset = availabilityTimeOffset;
            return this;
        }

        public Builder withAvailabilityTimeComplete(Boolean availabilityTimeComplete) {
            this.availabilityTimeComplete = availabilityTimeComplete;
            return this;
        }

        public BaseURL build() {
            return new BaseURL(value, serviceLocation, byteRange, availabilityTimeOffset, availabilityTimeComplete);
        }
    }
}

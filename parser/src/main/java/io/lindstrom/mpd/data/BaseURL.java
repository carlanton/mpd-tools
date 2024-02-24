package io.lindstrom.mpd.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JsonDeserialize(builder = BaseURL.Builder.class)
public record BaseURL(
        @JacksonXmlText String value,
        @JacksonXmlProperty(isAttribute = true) String serviceLocation,
        @JacksonXmlProperty(isAttribute = true) String byteRange,
        @JacksonXmlProperty(isAttribute = true) Double availabilityTimeOffset,
        @JacksonXmlProperty(isAttribute = true) Boolean availabilityTimeComplete
) {
    public Builder buildUpon() {
        return builder().from(this);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        @JacksonXmlText private String value;
        private String serviceLocation;
        private String byteRange;
        private Double availabilityTimeOffset;
        private Boolean availabilityTimeComplete;

        private Builder() {
        }

        @SuppressWarnings("unused")
        private Builder(String value) {
            this.value = value;
        }

        public Builder from(BaseURL instance) {
            this.value = instance.value();
            this.serviceLocation = instance.serviceLocation();
            this.byteRange = instance.byteRange();
            this.availabilityTimeOffset = instance.availabilityTimeOffset();
            this.availabilityTimeComplete = instance.availabilityTimeComplete();
            return this;
        }

        public Builder value(String value) {
            this.value = value;
            return this;
        }

        public Builder serviceLocation(String serviceLocation) {
            this.serviceLocation = serviceLocation;
            return this;
        }

        public Builder byteRange(String byteRange) {
            this.byteRange = byteRange;
            return this;
        }

        public Builder availabilityTimeOffset(Double availabilityTimeOffset) {
            this.availabilityTimeOffset = availabilityTimeOffset;
            return this;
        }

        public Builder availabilityTimeComplete(Boolean availabilityTimeComplete) {
            this.availabilityTimeComplete = availabilityTimeComplete;
            return this;
        }

        public BaseURL build() {
            return new BaseURL(value, serviceLocation, byteRange, availabilityTimeOffset, availabilityTimeComplete);
        }
    }
}
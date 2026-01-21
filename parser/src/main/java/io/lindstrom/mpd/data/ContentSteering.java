package io.lindstrom.mpd.data;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import java.net.URI;
import java.util.Objects;

public class ContentSteering {
    @JacksonXmlText
    private final String value;

    @JacksonXmlProperty(isAttribute = true)
    private final String defaultServiceLocation;

    @JacksonXmlProperty(isAttribute = true)
    private final Boolean queryBeforeStart;

    @JacksonXmlProperty(isAttribute = true)
    private final Boolean clientRequirement;

    private ContentSteering(String value, String defaultServiceLocation, Boolean queryBeforeStart, Boolean clientRequirement) {
        this.value = value;
        this.defaultServiceLocation = defaultServiceLocation;
        this.queryBeforeStart = queryBeforeStart;
        this.clientRequirement = clientRequirement;
    }

    private ContentSteering() {
        this.value = null;
        this.defaultServiceLocation = null;
        this.queryBeforeStart = null;
        this.clientRequirement = null;
    }

    public String getValue() {
        return value;
    }

    public String getDefaultServiceLocation() {
        return defaultServiceLocation;
    }

    public Boolean getQueryBeforeStart() {
        return queryBeforeStart;
    }

    public Boolean getClientRequirement() {
        return clientRequirement;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ContentSteering that)) return false;
        return Objects.equals(value, that.value) && Objects.equals(defaultServiceLocation, that.defaultServiceLocation) && Objects.equals(queryBeforeStart, that.queryBeforeStart) && Objects.equals(clientRequirement, that.clientRequirement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, defaultServiceLocation, queryBeforeStart, clientRequirement);
    }

    @Override
    public String toString() {
        return "ContentSteering{" +
                "value='" + value + '\'' +
                ", defaultServiceLocation='" + defaultServiceLocation + '\'' +
                ", queryBeforeStart=" + queryBeforeStart +
                ", clientRequirement=" + clientRequirement +
                '}';
    }

    public Builder buildUpon() {
        return new Builder()
                .withValue(value)
                .withDefaultServiceLocation(defaultServiceLocation)
                .withQueryBeforeStart(queryBeforeStart)
                .withClientRequirement(clientRequirement);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String value;
        private String defaultServiceLocation;
        private Boolean queryBeforeStart;
        private Boolean clientRequirement;

        public Builder withValue(String value) {
            this.value = value;
            return this;
        }

        public Builder withDefaultServiceLocation(String defaultServiceLocation) {
            this.defaultServiceLocation = defaultServiceLocation;
            return this;
        }

        public Builder withQueryBeforeStart(Boolean queryBeforeStart) {
            this.queryBeforeStart = queryBeforeStart;
            return this;
        }

        public Builder withClientRequirement(Boolean clientRequirement) {
            this.clientRequirement = clientRequirement;
            return this;
        }

        public ContentSteering build() {
            return new ContentSteering(value, defaultServiceLocation, queryBeforeStart, clientRequirement);
        }
    }
}
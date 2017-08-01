package io.lindstrom.mpd.data;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.Objects;

public class URLType {
    @XmlAttribute(name = "sourceURL")
    private final String sourceURL;

    @XmlAttribute(name = "range")
    private final String range;

    private URLType(String sourceURL, String range) {
        this.sourceURL = sourceURL;
        this.range = range;
    }

    @SuppressWarnings("unused")
    private URLType() {
        this.sourceURL = null;
        this.range = null;
    }

    public String getSourceURL() {
        return sourceURL;
    }

    public String getRange() {
        return range;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        URLType urlType = (URLType) o;
        return Objects.equals(sourceURL, urlType.sourceURL) &&
                Objects.equals(range, urlType.range);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceURL, range);
    }

    @Override
    public String toString() {
        return "URLType{" +
                "sourceURL='" + sourceURL + '\'' +
                ", range='" + range + '\'' +
                '}';
    }

    public Builder buildUpon() {
        return new Builder()
                .withSourceURL(sourceURL)
                .withRange(range);
    }

    public static class Builder {
        private String sourceURL;
        private String range;

        public Builder withSourceURL(String sourceURL) {
            this.sourceURL = sourceURL;
            return this;
        }

        public Builder withRange(String range) {
            this.range = range;
            return this;
        }

        public URLType build() {
            return new URLType(sourceURL, range);
        }
    }
}

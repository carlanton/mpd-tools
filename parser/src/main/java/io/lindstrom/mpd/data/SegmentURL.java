package io.lindstrom.mpd.data;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.Objects;


public class SegmentURL {
    @XmlAttribute(name = "media")
    private final String media;

    @XmlAttribute(name = "mediaRange")
    private final String mediaRange;

    @XmlAttribute(name = "index")
    private final String index;

    @XmlAttribute(name = "indexRange")
    private final String indexRange;

    private SegmentURL(String media, String mediaRange, String index, String indexRange) {
        this.media = media;
        this.mediaRange = mediaRange;
        this.index = index;
        this.indexRange = indexRange;
    }

    @SuppressWarnings("unused")
    private SegmentURL() {
        this.media = null;
        this.mediaRange = null;
        this.index = null;
        this.indexRange = null;
    }

    public String getMedia() {
        return media;
    }

    public String getMediaRange() {
        return mediaRange;
    }

    public String getIndex() {
        return index;
    }

    public String getIndexRange() {
        return indexRange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SegmentURL that = (SegmentURL) o;
        return Objects.equals(media, that.media) &&
                Objects.equals(mediaRange, that.mediaRange) &&
                Objects.equals(index, that.index) &&
                Objects.equals(indexRange, that.indexRange);
    }

    @Override
    public int hashCode() {
        return Objects.hash(media, mediaRange, index, indexRange);
    }

    @Override
    public String toString() {
        return "SegmentURL{" +
                "media='" + media + '\'' +
                ", mediaRange='" + mediaRange + '\'' +
                ", index='" + index + '\'' +
                ", indexRange='" + indexRange + '\'' +
                '}';
    }

    public Builder buildUpon() {
        return new Builder()
                .withMedia(media)
                .withMediaRange(mediaRange)
                .withIndex(index)
                .withIndexRange(indexRange);
    }

    public static class Builder {
        private String media;
        private String mediaRange;
        private String index;
        private String indexRange;

        public Builder withMedia(String media) {
            this.media = media;
            return this;
        }

        public Builder withMediaRange(String mediaRange) {
            this.mediaRange = mediaRange;
            return this;
        }

        public Builder withIndex(String index) {
            this.index = index;
            return this;
        }

        public Builder withIndexRange(String indexRange) {
            this.indexRange = indexRange;
            return this;
        }

        public SegmentURL build() {
            return new SegmentURL(media, mediaRange, index, indexRange);
        }
    }
}

package io.lindstrom.mpd.data;

import javax.xml.bind.annotation.XmlAttribute;
import java.time.Duration;

public class Range {
    @XmlAttribute(name = "starttime")
    private final Duration starttime;

    @XmlAttribute(name = "duration")
    private final Duration duration;

    private Range(Duration starttime, Duration duration) {
        this.starttime = starttime;
        this.duration = duration;
    }

    @SuppressWarnings("unused")
    private Range() {
        this.starttime = null;
        this.duration = null;
    }

    public Builder buildUpon() {
        return new Builder()
                .withStarttime(starttime)
                .withDuration(duration);
    }

    public static class Builder {
        private Duration starttime;
        private Duration duration;

        public Builder withStarttime(Duration starttime) {
            this.starttime = starttime;
            return this;
        }

        public Builder withDuration(Duration duration) {
            this.duration = duration;
            return this;
        }

        public Range build() {
            return new Range(starttime, duration);
        }
    }
}

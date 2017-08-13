package io.lindstrom.mpd.data;

import io.lindstrom.mpd.data.descriptor.Descriptor;
import io.lindstrom.mpd.support.Utils;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import java.util.Objects;

public class Metrics {
    @XmlElement(name = "Reporting", required = true, namespace = MPD.NAMESPACE)
    private final List<Descriptor> reportings;

    @XmlElement(name = "Range", namespace = MPD.NAMESPACE)
    private final List<Range> ranges;

    @XmlAttribute(name = "metrics", required = true)
    private final String metrics;

    private Metrics(List<Descriptor> reportings, List<Range> ranges, String metrics) {
        this.reportings = reportings;
        this.ranges = ranges;
        this.metrics = metrics;
    }

    @SuppressWarnings("unused")
    private Metrics() {
        this.reportings = null;
        this.ranges = null;
        this.metrics = null;
    }

    public List<Descriptor> getReportings() {
        return Utils.unmodifiableList(reportings);
    }

    public List<Range> getRanges() {
        return Utils.unmodifiableList(ranges);
    }

    public String getMetrics() {
        return metrics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Metrics metrics1 = (Metrics) o;
        return Objects.equals(reportings, metrics1.reportings) &&
                Objects.equals(ranges, metrics1.ranges) &&
                Objects.equals(metrics, metrics1.metrics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reportings, ranges, metrics);
    }

    @Override
    public String toString() {
        return "Metrics{" +
                "reportings=" + reportings +
                ", ranges=" + ranges +
                ", metrics='" + metrics + '\'' +
                '}';
    }

    public Builder buildUpon() {
        return new Builder()
                .withReportings(reportings)
                .withRanges(ranges)
                .withMetrics(metrics);
    }

    public static class Builder {
        private List<Descriptor> reportings;
        private List<Range> ranges;
        private String metrics;

        public Builder withReportings(List<Descriptor> reportings) {
            this.reportings = reportings;
            return this;
        }

        public Builder withRanges(List<Range> ranges) {
            this.ranges = ranges;
            return this;
        }

        public Builder withMetrics(String metrics) {
            this.metrics = metrics;
            return this;
        }

        public Metrics build() {
            return new Metrics(reportings, ranges, metrics);
        }
    }
}

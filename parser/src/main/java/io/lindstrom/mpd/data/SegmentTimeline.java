package io.lindstrom.mpd.data;

import io.lindstrom.mpd.support.Utils;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import java.util.Objects;

public class SegmentTimeline {
    @XmlElement(name = "S", required = true, namespace = MPD.NAMESPACE)
    private final List<S> ss;

    private SegmentTimeline(List<S> ss) {
        this.ss = ss;
    }

    @SuppressWarnings("unused")
    private SegmentTimeline() {
        this.ss = null;
    }

    public List<S> getSs() {
        return Utils.unmodifiableList(ss);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SegmentTimeline that = (SegmentTimeline) o;
        return Objects.equals(ss, that.ss);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ss);
    }

    @Override
    public String toString() {
        return "SegmentTimeline{" +
                "ss=" + ss +
                '}';
    }

    public Builder buildUpon() {
        return new Builder()
                .withSs(ss);
    }

    public static class Builder {
        private List<S> ss;

        public Builder withSs(List<S> ss) {
            this.ss = ss;
            return this;
        }

        public SegmentTimeline build() {
            return new SegmentTimeline(ss);
        }
    }

    public static class S {
        @XmlAttribute(name = "t")
        private final Long t;

        @XmlAttribute(name = "n")
        private final Long n;

        @XmlAttribute(name = "d", required = true)
        private final long d;

        @XmlAttribute(name = "r")
        private final Long r;

        private S(Long t, Long n, long d, Long r) {
            this.t = t;
            this.n = n;
            this.d = d;
            this.r = r;
        }

        @SuppressWarnings("unused")
        private S() {
            this.t = null;
            this.n = null;
            this.d = 0;
            this.r = null;
        }

        public Long getT() {
            return t;
        }

        public Long getN() {
            return n;
        }

        public long getD() {
            return d;
        }

        public Long getR() {
            return r;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            S s = (S) o;
            return d == s.d &&
                    Objects.equals(t, s.t) &&
                    Objects.equals(n, s.n) &&
                    Objects.equals(r, s.r);
        }

        @Override
        public int hashCode() {
            return Objects.hash(t, n, d, r);
        }

        @Override
        public String toString() {
            return "S{" +
                    "t=" + t +
                    ", n=" + n +
                    ", d=" + d +
                    ", r=" + r +
                    '}';
        }

        public Builder buildUpon() {
            return new Builder()
                    .withT(t)
                    .withN(n)
                    .withD(d)
                    .withR(r);
        }

        public static class Builder {
            private Long t;
            private Long n;
            private long d;
            private Long r;

            public Builder withT(Long t) {
                this.t = t;
                return this;
            }

            public Builder withN(Long n) {
                this.n = n;
                return this;
            }

            public Builder withD(long d) {
                this.d = d;
                return this;
            }

            public Builder withR(Long r) {
                this.r = r;
                return this;
            }

            public S build() {
                return new S(t, n, d, r);
            }
        }
    }
}

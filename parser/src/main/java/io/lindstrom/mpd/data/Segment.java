package io.lindstrom.mpd.data;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.Objects;

public class Segment {
    @XmlAttribute(name = "t")
    private final Long t;

    @XmlAttribute(name = "n")
    private final Long n;

    @XmlAttribute(name = "d", required = true)
    private final long d;

    @XmlAttribute(name = "r")
    private final Long r;

    private Segment(Long t, Long n, long d, Long r) {
        this.t = t;
        this.n = n;
        this.d = d;
        this.r = r;
    }

    @SuppressWarnings("unused")
    private Segment() {
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
        Segment s = (Segment) o;
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

        public Segment build() {
            return new Segment(t, n, d, r);
        }
    }
}

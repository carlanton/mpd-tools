package io.lindstrom.mpd.data;

import io.lindstrom.mpd.support.VectorAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;
import java.util.Objects;

public class Subset {
    @XmlAttribute(name = "contains", required = true)
    @XmlJavaTypeAdapter(VectorAdapter.LongAdapter.class)
    private final List<Long> contains;

    @XmlAttribute(name = "id")
    private final String id;

    private Subset(List<Long> contains, String id) {
        this.contains = contains;
        this.id = id;
    }

    @SuppressWarnings("unused")
    private Subset() {
        this.contains = null;
        this.id = null;
    }

    public List<Long> getContains() {
        return contains;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subset subset = (Subset) o;
        return Objects.equals(contains, subset.contains) &&
                Objects.equals(id, subset.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contains, id);
    }

    @Override
    public String toString() {
        return "Subset{" +
                "contains=" + contains +
                ", id='" + id + '\'' +
                '}';
    }

    public Builder buildUpon() {
        return new Builder()
                .withContains(contains)
                .withId(id);
    }

    public static class Builder {
        private List<Long> contains;
        private String id;

        public Builder withContains(List<Long> contains) {
            this.contains = contains;
            return this;
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Subset build() {
            return new Subset(contains, id);
        }
    }
}

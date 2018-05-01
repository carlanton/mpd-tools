package io.lindstrom.mpd.data;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Objects;

public class Subset {
    @JacksonXmlProperty(isAttribute = true)
    private final String contains;

    @JacksonXmlProperty(isAttribute = true)
    private final String id;

    private Subset(String contains, String id) {
        this.contains = contains;
        this.id = id;
    }

    @SuppressWarnings("unused")
    private Subset() {
        this.contains = null;
        this.id = null;
    }

    public String getContains() {
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String contains;
        private String id;

        public Builder withContains(String contains) {
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

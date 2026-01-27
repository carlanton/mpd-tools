package io.lindstrom.mpd.data;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import java.util.Objects;

public class Label {
    @JacksonXmlText
    private final String value;

    @JacksonXmlProperty(isAttribute = true)
    private final String id;

    @JacksonXmlProperty(isAttribute = true)
    private final String lang;

    private Label(String value, String id, String lang) {
        this.value = value;
        this.id = id;
        this.lang = lang;
    }

    @SuppressWarnings("unused")
    private Label() {
        this.value = null;
        this.id = null;
        this.lang = null;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Label label)) return false;
        return Objects.equals(value, label.value) && Objects.equals(id, label.id) && Objects.equals(lang, label.lang);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, id, lang);
    }

    @Override
    public String toString() {
        return "Label{" +
                "value='" + value + '\'' +
                ", id='" + id + '\'' +
                ", lang='" + lang + '\'' +
                '}';
    }

    public String getValue() {
        return value;
    }

    public String getId() {
        return id;
    }

    public String getLang() {
        return lang;
    }

    public Builder buildUpon() {
        return new Builder()
                .withValue(value)
                .withId(id)
                .withLang(lang);
    }

    public static Label of(String value) {
        return new Label(value, null, null);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String value;
        private String id;
        private String lang;

        public Builder withValue(String value) {
            this.value = value;
            return this;
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withLang(String lang) {
            this.lang = lang;
            return this;
        }

        public Label build() {
            return new Label(value, id, lang);
        }
    }
}
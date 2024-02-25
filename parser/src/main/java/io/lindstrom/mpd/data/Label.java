package io.lindstrom.mpd.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JsonDeserialize(builder = Label.Builder.class)
public record Label(
        @JacksonXmlText String value,
        @JacksonXmlProperty(isAttribute = true) Long id,
        @JacksonXmlProperty(isAttribute = true) String lang) {

    public static Label of(String value) {
        return new Label(value, null, null);
    }

    public Builder buildUpon() {
        return builder().from(this);
    }

    static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        @JacksonXmlText private String value;
        private Long id;
        private String lang;

        private Builder() {
        }

        @SuppressWarnings("unused")
        private Builder(String value) {
            this.value = value;
        }

        public Builder value(String value) {
            this.value = value;
            return this;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder lang(String lang) {
            this.lang = lang;
            return this;
        }

        public Builder from(Label instance) {
            this.value = instance.value();
            this.id = instance.id();
            this.lang = instance.lang();
            return this;
        }

        public Label build() {
            return new Label(value, id, lang);
        }
    }
}
package io.lindstrom.mpd.data.descriptor;


import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Locale;
import java.util.Objects;

public class Role extends Descriptor {
    static final String SCHEME_ID_URI = "urn:mpeg:dash:role:2011";

    @JacksonXmlProperty(localName = "value", isAttribute = true)
    private final Type type;

    public Role(Type value) {
        super(SCHEME_ID_URI, null);
        this.type = value;
    }

    public Role(Type value, String id) {
        super(SCHEME_ID_URI, id);
        this.type = value;
    }

    @SuppressWarnings("unused")
    private Role() {
        super(null, null);
        this.type = null;
    }

    @Override
    public String getValue() {
        if (type == null) {
            return null;
        } else {
            return type.toString().toLowerCase(Locale.US);
        }
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        @JsonProperty("caption") CAPTION,
        @JsonProperty("subtitle") SUBTITLE,
        @JsonProperty("main") MAIN,
        @JsonProperty("alternate") ALTERNATE,
        @JsonProperty("supplementary") SUPPLEMENTARY,
        @JsonProperty("commentary") COMMENTARY,
        @JsonProperty("dub") DUB,
        @JsonProperty("description") DESCRIPTION,
        @JsonProperty("enhanced-audio-intelligibility") ENHANCED_AUDIO_INTELLIGIBILITY,
        @JsonProperty("emergency") EMERGENCY,
        @JsonProperty("sign") SIGN,

        @JsonEnumDefaultValue UNKNOWN
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Role role = (Role) o;
        return type == role.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }

    @Override
    public String toString() {
        return "Role{" +
                "type=" + type +
                ", schemeIdUri='" + schemeIdUri + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public Builder buildUpon() {
        return new Builder()
                .withId(id)
                .withType(type);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Type type;
        private String id;

        public Builder withType(Type type) {
            this.type = type;
            return this;
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Role build() {
            return new Role(type, id);
        }
    }
}

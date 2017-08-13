package io.lindstrom.mpd.data.descriptor;


import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.Objects;

public class Role extends Descriptor {
    static final String SCHEME_ID_URI = "urn:mpeg:dash:role:2011";

    @XmlAttribute(name = "value")
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
            return type.toString().toLowerCase();
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
}

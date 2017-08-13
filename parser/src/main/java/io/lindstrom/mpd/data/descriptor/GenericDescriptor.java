package io.lindstrom.mpd.data.descriptor;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.Objects;

public class GenericDescriptor extends Descriptor {
    @XmlAttribute(name = "value")
    private final String value;

    public GenericDescriptor(String schemeIdUri, String value, String id) {
        super(schemeIdUri, id);
        this.value = value;
    }

    public GenericDescriptor(String schemeIdUri, String value) {
        this(schemeIdUri, value, null);
    }

    @SuppressWarnings("unused")
    private GenericDescriptor() {
        super(null, null);
        this.value = null;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GenericDescriptor that = (GenericDescriptor) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value);
    }

    @Override
    public String toString() {
        return "GenericDescriptor{" +
                "value='" + value + '\'' +
                ", schemeIdUri='" + schemeIdUri + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}

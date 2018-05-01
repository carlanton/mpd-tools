package io.lindstrom.mpd.data.descriptor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.lindstrom.mpd.data.descriptor.protection.Mp4Protection;
import io.lindstrom.mpd.data.descriptor.protection.PlayReadyContentProtection;
import io.lindstrom.mpd.data.descriptor.protection.WidewineProtection;

import java.util.Objects;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "schemeIdUri",
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        visible = true,
        defaultImpl = GenericDescriptor.class)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Role.class, name = Role.SCHEME_ID_URI),
        @JsonSubTypes.Type(value = Mp4Protection.class, name = Mp4Protection.SCHEME_ID_URI),
        @JsonSubTypes.Type(value = PlayReadyContentProtection.class, name = PlayReadyContentProtection.SCHEME_ID_URI),
        @JsonSubTypes.Type(value = WidewineProtection.class, name = WidewineProtection.SCHEME_ID_URI)
})
public abstract class Descriptor {
    @JacksonXmlProperty(isAttribute = true)
    protected final String schemeIdUri;

    @JacksonXmlProperty(isAttribute = true)
    protected final String id;

    protected Descriptor(String schemeIdUri, String id) {
        this.schemeIdUri = schemeIdUri;
        this.id = id;
    }

    public String getSchemeIdUri() {
        return schemeIdUri;
    }

    public String getId() {
        return id;
    }

    @JsonIgnore
    public abstract String getValue();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Descriptor)) return false;
        Descriptor that = (Descriptor) o;
        return Objects.equals(schemeIdUri, that.schemeIdUri) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schemeIdUri, id);
    }
}

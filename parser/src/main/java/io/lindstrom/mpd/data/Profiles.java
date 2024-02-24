package io.lindstrom.mpd.data;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Value.Immutable
@JsonSerialize(using = Profiles.Serializer.class)
@JsonDeserialize(using = Profiles.Deserializer.class)
public interface Profiles {
    List<Profile> profiles();
    List<String> interoperabilityPointsAndExtensions();

    default Builder buildUpon() {
        return builder().from(this);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableProfiles.Builder {}


    class Serializer extends JsonSerializer<Profiles> {
        @Override
        public void serialize(Profiles value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            List<String> list = new ArrayList<>();
            value.profiles().forEach(profile -> list.add(profile.toString()));
            list.addAll(value.interoperabilityPointsAndExtensions());
            gen.writeString(String.join(",", list));
        }
    }

    class Deserializer extends JsonDeserializer<Profiles> {
        @Override
        public Profiles deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            String text = p.getText();

            List<Profile> profiles = new ArrayList<>();
            List<String> others = new ArrayList<>();

            for (String identifier : text.split(",")) {
                identifier = identifier.trim();

                try {
                    profiles.add(Profile.fromIdentifier(identifier));
                } catch (IllegalArgumentException e) {
                    others.add(identifier);
                }
            }

            return builder()
                    .profiles(profiles)
                    .interoperabilityPointsAndExtensions(others)
                    .build();
        }
    }
}
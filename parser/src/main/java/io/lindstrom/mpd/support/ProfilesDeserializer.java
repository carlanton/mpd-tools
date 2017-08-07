package io.lindstrom.mpd.support;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.lindstrom.mpd.data.Profile;
import io.lindstrom.mpd.data.Profiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProfilesDeserializer extends JsonDeserializer<Profiles> {
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

        return new Profiles(profiles, others);
    }
}

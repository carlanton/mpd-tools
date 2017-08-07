package io.lindstrom.mpd.support;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.lindstrom.mpd.data.Profiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProfilesSerializer extends JsonSerializer<Profiles> {
    @Override
    public void serialize(Profiles value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        List<String> list = new ArrayList<>();
        value.getProfiles().forEach(profile -> list.add(profile.toString()));
        list.addAll(value.getInteroperabilityPointsAndExtensions());
        gen.writeString(String.join(",", list));
    }
}

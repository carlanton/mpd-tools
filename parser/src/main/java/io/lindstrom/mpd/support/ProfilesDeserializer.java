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
  return null;
    }
}
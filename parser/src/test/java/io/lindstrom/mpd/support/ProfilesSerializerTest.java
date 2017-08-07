package io.lindstrom.mpd.support;

import io.lindstrom.mpd.data.Profile;
import io.lindstrom.mpd.data.Profiles;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class ProfilesSerializerTest extends ObjectMapperTestBase<Profiles> {
    public ProfilesSerializerTest() {
        super(Profiles.class, new ProfilesSerializer(), new ProfilesDeserializer());
    }

    @Test
    public void serialize1() throws Exception {
        Profiles profiles = new Profiles(Arrays.asList(Profile.MPEG_DASH_LIVE, Profile.HBBTV15),
                Collections.singletonList("http://dashif.org/guidelines/dash264high"));

        assertEquals("urn:mpeg:dash:profile:isoff-live:2011,urn:hbbtv:dash:profile:isoff-live:2012,http://dashif.org/guidelines/dash264high",
                write(profiles));
    }

    @Test
    public void deserialize1() throws Exception {
        Profiles profiles = new Profiles(Arrays.asList(Profile.MPEG_DASH_LIVE, Profile.HBBTV15),
                Collections.singletonList("http://dashif.org/guidelines/dash264high"));

        assertEquals(profiles, read("urn:mpeg:dash:profile:isoff-live:2011,urn:hbbtv:dash:profile:isoff-live:2012,http://dashif.org/guidelines/dash264high"));
    }
}
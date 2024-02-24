package io.lindstrom.mpd.support;

import io.lindstrom.mpd.data.Profile;
import io.lindstrom.mpd.data.Profiles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfilesSerializerTest extends ObjectMapperTestBase<Profiles> {

    @BeforeEach
    public void ProfilesSerializerTest() {
        initObjectMapperTestBase(Profiles.class, new ProfilesSerializer(), new ProfilesDeserializer());
    }

    @Test
    public void serialize1() throws Exception {
        Profiles profiles = Profiles.builder()
                .addProfiles(Profile.MPEG_DASH_LIVE, Profile.HBBTV15)
                .addInteroperabilityPointsAndExtensions("http://dashif.org/guidelines/dash264high")
                .build();

        assertEquals("urn:mpeg:dash:profile:isoff-live:2011,urn:hbbtv:dash:profile:isoff-live:2012,http://dashif.org/guidelines/dash264high",
                write(profiles));
    }

    @Test
    public void deserialize1() throws Exception {
        Profiles profiles = Profiles.builder()
                .addProfiles(Profile.MPEG_DASH_LIVE, Profile.HBBTV15)
                .addInteroperabilityPointsAndExtensions("http://dashif.org/guidelines/dash264high")
                .build();

        assertEquals(profiles, read("urn:mpeg:dash:profile:isoff-live:2011,urn:hbbtv:dash:profile:isoff-live:2012,http://dashif.org/guidelines/dash264high"));
    }
}
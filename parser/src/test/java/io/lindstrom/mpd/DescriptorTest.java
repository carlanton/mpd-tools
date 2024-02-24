package io.lindstrom.mpd;

import io.lindstrom.mpd.data.AdaptationSet;
import io.lindstrom.mpd.data.MPD;
import io.lindstrom.mpd.data.Period;
import io.lindstrom.mpd.data.PresentationType;
import io.lindstrom.mpd.data.descriptor.Descriptor;
import io.lindstrom.mpd.data.descriptor.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DescriptorTest {
    private final String INPUT = "<MPD xmlns=\"urn:mpeg:dash:schema:mpd:2011\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"urn:mpeg:dash:schema:mpd:2011 http://standards.iso.org/ittf/PubliclyAvailableStandards/MPEG-DASH_schema_files/DASH-MPD.xsd\" type=\"static\" minBufferTime=\"PT0S\">\n" +
            "  <Period>\n" +
            "    <AdaptationSet>\n" +
            "      <Role schemeIdUri=\"urn:mpeg:dash:role:2011\" id=\"3\" value=\"dub\"/>\n" +
            "    </AdaptationSet>\n" +
            "  </Period>\n" +
            "</MPD>\n";

    private final Role ROLE = Role.builder().type(Role.Type.DUB).id("3").build();
    //new Role(Role.Type.DUB, "3");

    private final MPD OUTPUT;

    {
        OUTPUT = new MPD.Builder()
                .schemaLocation(MPD.DEFAULT_SCHEMA_LOCATION)
                .type(PresentationType.STATIC)
                .minBufferTime(Duration.ZERO)
                .addPeriods(Period.builder()
                        .addAdaptationSets(AdaptationSet.builder()
                                .addRoles(ROLE)
                                .build())
                        .build())
                .build();
    }

    @Test
    public void deserialize() throws Exception {
        Descriptor descriptor = new MPDParser()
                .parse(INPUT)
                .periods().get(0)
                .adaptationSets().get(0)
                .roles().get(0);

        Assertions.assertTrue(descriptor instanceof Role, "Should be Role");
        Role role = (Role) descriptor;
        assertEquals(ROLE, role);
    }

    @Test
    public void serialize() throws Exception {
        assertEquals(INPUT, new MPDParser().writeAsString(OUTPUT));
    }
}
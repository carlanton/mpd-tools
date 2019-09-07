package io.lindstrom.mpd;

import io.lindstrom.mpd.data.AdaptationSet;
import io.lindstrom.mpd.data.MPD;
import io.lindstrom.mpd.data.Period;
import io.lindstrom.mpd.data.descriptor.Descriptor;
import io.lindstrom.mpd.data.descriptor.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DescriptorTest {
    private final String INPUT = "<MPD xmlns=\"urn:mpeg:dash:schema:mpd:2011\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"urn:mpeg:dash:schema:mpd:2011 http://standards.iso.org/ittf/PubliclyAvailableStandards/MPEG-DASH_schema_files/DASH-MPD.xsd\" type=\"static\" minBufferTime=\"PT0S\">\n" +
            "  <Period>\n" +
            "    <AdaptationSet>\n" +
            "      <Role schemeIdUri=\"urn:mpeg:dash:role:2011\" id=\"3\" value=\"dub\"/>\n" +
            "    </AdaptationSet>\n" +
            "  </Period>\n" +
            "</MPD>\n";

    private final Role ROLE = new Role(Role.Type.DUB, "3");

    private final MPD OUTPUT = new MPD.Builder()
            .withPeriods(new Period.Builder()
                    .withAdaptationSet(new AdaptationSet.Builder()
                            .withRoles(ROLE)
                            .build())
                    .build())
            .build();

    @Test
    public void deserialize() throws Exception {
        Descriptor descriptor = new MPDParser()
                .parse(INPUT)
                .getPeriods().get(0)
                .getAdaptationSets().get(0)
                .getRoles().get(0);

        Assertions.assertTrue(descriptor instanceof Role, "Should be Role");
        Role role = (Role) descriptor;
        assertEquals(ROLE, role);
    }

    @Test
    public void serialize() throws Exception {
        assertEquals(INPUT, new MPDParser().writeAsString(OUTPUT));
    }
}

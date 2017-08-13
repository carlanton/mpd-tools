package io.lindstrom.mpd;

import io.lindstrom.mpd.data.MPD;
import org.junit.Test;

public class DescriptorTest {
    @Test
    public void name() throws Exception {
        String xml = "<MPD><Period><AdaptationSet><Role schemeIdUri=\"urn:mpeg:dash:role:20114\" id='3' value=\"dub\"/>" +
                "</AdaptationSet></Period></MPD>";

        MPDParser parser = new MPDParser();

        MPD mpd = parser.parse(xml);
        System.out.println(mpd.getPeriods().get(0).getAdaptationSets().get(0).getRoles());


        System.out.println(parser.writeAsString(mpd));
    }
}

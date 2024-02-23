package io.lindstrom.mpd;


import io.lindstrom.mpd.v2.BaseURL;
import io.lindstrom.mpd.v2.MPD;
import io.lindstrom.mpd.v2.Period;
import io.lindstrom.mpd.v2.UTCTiming;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasicTest {
    @Test
    void name() throws IOException {
        MPDParserV2 parser = new MPDParserV2();

        MPD mpd = MPD.builder()
                .schemaLocation("urn:mpeg:dash:schema:mpd:2011")
                .id("123")
                .availabilityStartTime(OffsetDateTime.of(2024, 1, 1, 0, 0,0, 0,  ZoneOffset.UTC))
                .addBaseURLs(BaseURL.builder()
                        .value("https://what-ever.com/")
                        .byteRange("yes")
                        .build())

                .addBaseURLs(BaseURL.builder()
                        .value("https://what-ever2.com/")
                        .build())

                .addPeriods(Period.builder()
                        .id("1")
                        .bitstreamSwitching(true)
                        .duration(Duration.ofSeconds(60))
                        .build())

                .addUtcTimings(UTCTiming.builder()
                        .id("wow")
                        .schemeIdUri(UTCTiming.Type.HTTP_HEAD)
                        .value("ok sir")
                        .build())
                .addUtcTimings(UTCTiming.builder()
                        .id("2")
                        .schemeIdUri(UTCTiming.Type.HTTP_HEAD)
                        .value("ok sir 2")
                        .build())
                .build();

        String str = parser.writeAsString(mpd);

        System.out.println(str);


        MPD p = parser.parse(str);
        System.out.println(p);

        assertEquals(mpd, p);
        System.out.println(str);


    }
}
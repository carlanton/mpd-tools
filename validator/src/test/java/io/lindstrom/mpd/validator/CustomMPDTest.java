package io.lindstrom.mpd.validator;

import io.lindstrom.mpd.data.*;
import org.junit.Test;

import java.time.Duration;
import java.util.Arrays;

public class CustomMPDTest {
    @Test
    public void test1() throws Exception {
        MPD mpd = new MPD.Builder()
                .withProfiles(Profile.MPEG_DASH_ON_DEMAND, Profile.HBBTV15)
                .withInteroperabilityPointsAndExtensions("mupp2k")
                .withMediaPresentationDuration(Duration.ofHours(1))
                .withPeriods(new Period.Builder()
                        .withAdaptationSet(new AdaptationSet.Builder()
                                .withId(1L)
                                .withMimeType("video/mp4")
                                .withContentType("video")
                                .withWidth(1024L)
                                .withHeight(1024L)
                                .withFrameRate(new FrameRate(25, null))
                                .withPar(new Ratio(1L,1L))
                                .withSubsegmentAlignment("true")
                                .withRepresentations(new Representation.Builder()
                                        .withId("x")
                                        .withBandwidth(4)
                                        .withSegmentTemplate(new SegmentTemplate.Builder()
                                                .withSegmentTimeline(Arrays.asList(
                                                        new Segment.Builder().withD(4).build(),
                                                        new Segment.Builder().withT(5L).build()
                                                ))
                                                .build())
                                        .build())
                                .build(),
                                new AdaptationSet.Builder()
                                        .withId(2L)
                                        .withWidth(1024L)
                                        .withHeight(1024L)
                                        .withFrameRate(new FrameRate(25, null))
                                        .withMimeType("video/mp4")
                                        .withContentType("video")
                                        .withPar(new Ratio(1L,1L))
                                        .withSubsegmentAlignment("true")
                                        .withRepresentations(new Representation.Builder()
                                                .withId("x")
                                                .withBandwidth(4)
                                                .withSegmentTemplate(new SegmentTemplate.Builder()
                                                        .withSegmentTimeline(Arrays.asList(
                                                                new Segment.Builder().withD(4).build(),
                                                                new Segment.Builder().withT(5L).build()
                                                        ))
                                                        .build())
                                                .build())
                                        .build()
                                )
                        .build())
                .build();

        MPDValidator mpdValidator = new MPDValidator();
        mpdValidator.validate(mpd);
    }
}

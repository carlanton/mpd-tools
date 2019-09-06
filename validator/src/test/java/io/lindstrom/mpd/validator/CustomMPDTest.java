package io.lindstrom.mpd.validator;

import io.lindstrom.mpd.data.*;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class CustomMPDTest {
    @Test
    public void test1() throws Exception {
        MPD mpd = MPD.builder()
                .withProfiles(Profile.MPEG_DASH_ON_DEMAND, Profile.HBBTV15)
                .withInteroperabilityPointsAndExtensions("mupp2k")
                .withMediaPresentationDuration(Duration.ofHours(1))
                .withPeriods(Period.builder()
                        .withAdaptationSet(AdaptationSet.builder()
                                        .withId(1L)
                                        .withMimeType("video/mp4")
                                        .withContentType("video")
                                        .withWidth(1024L)
                                        .withHeight(1024L)
                                        .withFrameRate(FrameRate.of(25))
                                        .withPar(Ratio.of(1, 1))
                                        .withSubsegmentAlignment("true")
                                        .withRepresentations(Representation.builder()
                                                .withId("x")
                                                .withBandwidth(4)
                                                .withSegmentTemplate(SegmentTemplate.builder()
                                                        .withSegmentTimeline(Segment.of(0, 4), Segment.of(4, 4))
                                                        .build())
                                                .build())
                                        .build(),
                                AdaptationSet.builder()
                                        .withId(2)
                                        .withWidth(1024)
                                        .withHeight(1024)
                                        .withFrameRate(FrameRate.of(25))
                                        .withMimeType("video/mp4")
                                        .withContentType("video")
                                        .withPar(Ratio.of(1, 1))
                                        .withSubsegmentAlignment("true")
                                        .withRepresentations(Representation.builder()
                                                .withId("x")
                                                .withBandwidth(4)
                                                .withSegmentTemplate(SegmentTemplate.builder()
                                                        .withSegmentTimeline(Segment.of(0, 4), Segment.of(4, 4))
                                                        .build())
                                                .build())
                                        .build())
                        .build())
                .build();

        MPDValidator mpdValidator = new MPDValidator();
        mpdValidator.validate(mpd);
    }
}

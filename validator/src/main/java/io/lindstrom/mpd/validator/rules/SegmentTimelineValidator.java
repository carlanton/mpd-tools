package io.lindstrom.mpd.validator.rules;

import io.lindstrom.mpd.data.MPD;
import io.lindstrom.mpd.data.Segment;

import java.util.Collections;
import java.util.List;

public class SegmentTimelineValidator {
    @ValidationRule("if (" +
            "(if (ancestor::dash:*[1]/@timescale) then (child::dash:S/@d div ancestor::dash:*[1]/@timescale) else child::dash:S/@d) > (" +
            "years-from-duration(ancestor::dash:MPD/@maxSegmentDuration) + " +
            "months-from-duration(ancestor::dash:MPD/@maxSegmentDuration) + " +
            "days-from-duration(ancestor::dash:MPD/@maxSegmentDuration) + " +
            "hours-from-duration(ancestor::dash:MPD/@maxSegmentDuration) + " +
            "minutes-from-duration(ancestor::dash:MPD/@maxSegmentDuration) +  " +
            "seconds-from-duration(ancestor::dash:MPD/@maxSegmentDuration))) " +
            "then false() else true()")
    private static Violation ruleR100(MPD mpd, Long timescale, List<Segment> segmentTimeline) {
        if (mpd.getMaxSegmentDuration() == null) {
            return Violation.empty();
        }

        long maxSegmentDuration = mpd.getMaxSegmentDuration().toMillis() / 1000;

        for (Segment s : segmentTimeline) {
            double d = timescale == null ? s.getD() : (s.getD() / (double) timescale);

            if (d > maxSegmentDuration) {
                return new Violation("R10.0",
                        "The d attribute of a SegmentTimeline shall not exceed the value give bei the MPD maxSegmentDuration attribute.");
            }
        }
        return Violation.empty();
    }

    public static List<Violation> validate(MPD mpd, Long timescale, List<Segment> segmentTimeline) {
        return Collections.singletonList(ruleR100(mpd, timescale, segmentTimeline));
    }
}

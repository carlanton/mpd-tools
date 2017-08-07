package io.lindstrom.mpd.validator;

import io.lindstrom.mpd.data.MPD;
import io.lindstrom.mpd.data.SegmentList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SegmentListValidator {
    /*
     *
     * R8.*: Check the conformance of SegmentList
     *
     */

    @ValidationRule("if (not(@duration) and not(child::dash:SegmentTimeline)) then " +
            "if (count(child::dash:SegmentURL) > 1) then false() else true() else true()")
    private static Violation ruleR80(SegmentList segmentList) {
        if (segmentList.getDuration() == null && segmentList.getSegmentTimeline().isEmpty() && segmentList.getSegmentURLs().size() > 1) {
            return new Violation("R8.0",
                    "If more than one Media Segment is present the duration attribute or SegmentTimeline element shall be present.");

        }
        return Violation.empty();
    }

    @ValidationRule("if (@duration and child::dash:SegmentTimeline) then false() else true()")
    private static Violation ruleR81(SegmentList segmentList) {
        if (segmentList.getDuration() != null && !segmentList.getSegmentTimeline().isEmpty()) {
            return new Violation("R8.1", "Either the duration attribute or SegmentTimeline element shall be present but not both.");
        }
        return Violation.empty();
    }

    @ValidationRule("if (not(@indexRange) and @indexRangeExact) then false() else true()")
    private static Violation ruleR82(SegmentList segmentList) {
        if (segmentList.getIndexRange() == null && segmentList.getIndexRangeExact() != null) {
            return new Violation("R8.2", "If indexRange is not present indexRangeExact shall not be present.");
        }
        return Violation.empty();
    }

    public static List<Violation> validate(MPD mpd, SegmentList segmentList) {
        List<Violation> violations = new ArrayList<>();

        violations.addAll(Arrays.asList(ruleR80(segmentList), ruleR81(segmentList), ruleR82(segmentList)));

        violations.addAll(SegmentTimelineValidator.validate(mpd, segmentList.getTimescale(), segmentList.getSegmentTimeline()));

        return violations;
    }
}

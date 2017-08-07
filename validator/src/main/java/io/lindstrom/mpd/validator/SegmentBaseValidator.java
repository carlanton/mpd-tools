package io.lindstrom.mpd.validator;

import io.lindstrom.mpd.data.MPD;
import io.lindstrom.mpd.data.SegmentBase;

import java.util.Arrays;
import java.util.List;

public class SegmentBaseValidator {
    /*
     *
     * R9.*: Check the conformance of SegmentBase
     *
     */

    @ValidationRule("if (not(@indexRange) and @indexRangeExact) then false() else true()")
    private static Violation ruleR90(SegmentBase segmentBase) {
        if (segmentBase.getIndexRange() == null && segmentBase.getIndexRangeExact() != null) {
            return new Violation("R9.0", "If indexRange is not present indexRangeExact shall not be present.");
        }
        return Violation.empty();
    }

    @ValidationRule("if (@timeShiftBufferDepth) then " +
            "if (@timeShiftbuffer < dash:MPD/@timeShiftBufferDepth) then false() else true() " +
            "else true()")
    private static Violation ruleR91(MPD mpd, SegmentBase segmentBase) {
        // TODO: Can this element have a timeShiftBufferDepth?
        //return new Violation("R9.1", "The timeShiftBufferDepth shall not be smaller than timeShiftBufferDepth specified in the MPD element");
        return Violation.empty();
    }

   public static List<Violation> validate(MPD mpd, SegmentBase segmentBase) {
        return Arrays.asList(ruleR90(segmentBase), ruleR91(mpd, segmentBase));
   }
}

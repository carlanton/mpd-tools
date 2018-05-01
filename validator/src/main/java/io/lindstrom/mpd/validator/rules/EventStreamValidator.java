package io.lindstrom.mpd.validator.rules;

import io.lindstrom.mpd.data.EventStream;

import java.util.Arrays;
import java.util.List;

public class EventStreamValidator {
    /*
     *
     * R16.*: Check the conformance of SegmentList
     *
     */

    @ValidationRule("if (@actuate and not(@href)) then false() else true()")
    private static Violation ruleR160(EventStream eventStream) {
        if (eventStream.getActuate() != null && eventStream.getHref() == null) {
            return new Violation("R16.0", "If href is not present actuate shall not be present.");
        }
        return Violation.empty();
    }

    @ValidationRule("if (not(@schemeIdUri)) then false() else true()")
    private static Violation ruleR161(EventStream eventStream) {
        if (eventStream.getSchemeIdUri() == null) {
            return new Violation("R16.1", "schemeIdUri shall be present.");
        }
        return Violation.empty();
    }

    public static List<Violation> validate(EventStream eventStream) {
        return Arrays.asList(ruleR160(eventStream), ruleR161(eventStream));
    }
}

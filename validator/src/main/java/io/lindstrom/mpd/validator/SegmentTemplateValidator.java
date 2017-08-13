package io.lindstrom.mpd.validator;

import io.lindstrom.mpd.data.MPD;
import io.lindstrom.mpd.data.SegmentTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SegmentTemplateValidator {
    /*
     *
     * R7.*: Check the conformance of SegmentTemplate
     *
     */


    @ValidationRule("if (not(@duration) and not(child::dash:SegmentTimeline) and not(@initialization) ) then false() else true()")
    private static Violation ruleR70(SegmentTemplate segmentTemplate) {
        if (segmentTemplate.getDuration() == null && segmentTemplate.getSegmentTimeline().isEmpty() && segmentTemplate.getInitialization() == null) {
            return new Violation("R7.0", "If more than one Media Segment is present the duration attribute or SegmentTimeline element shall be present.");
        }
        return Violation.empty();
    }

    @ValidationRule("if (@duration and child::dash:SegmentTimeline) then false() else true()")
    private static Violation ruleR71(SegmentTemplate segmentTemplate) {
        if (segmentTemplate.getDuration() != null && !segmentTemplate.getSegmentTimeline().isEmpty()) {
            return new Violation("R7.1", "Either the duration attribute or SegmentTimeline element shall be present but not both.");
        }
        return Violation.empty();
    }

    @ValidationRule("if (not(@indexRange) and @indexRangeExact) then false() else true()")
    private static Violation ruleR72(SegmentTemplate segmentTemplate) {
        if (segmentTemplate.getIndexRange() == null && segmentTemplate.getIndexRangeExact() != null) {
            return new Violation("R7.2", "If indexRange is not present indexRangeExact shall not be present.");
        }
        return Violation.empty();
    }

    @ValidationRule("if (@initialization and (matches(@initialization, '\\$Number(%.[^\\$]*)?\\$') or matches(@initialization, '\\$Time(%.[^\\$]*)?\\$'))) then false() else true()")
    private static Violation ruleR73(SegmentTemplate segmentTemplate) {
        String initialization = segmentTemplate.getInitialization();
        if (initialization != null && (initialization.contains("$Number") || initialization.contains("$Time"))) {
            return new Violation("R7.3", "Neither $Number$ nor the $Time$ identifier shall be included in the initialization attribute.");
        }
        return Violation.empty();
    }

    @ValidationRule("if (@bitstreamSwitching and (matches(@bitstreamSwitching, '\\$Number(%.[^\\$]*)?\\$') or " +
            "matches(@bitstreamSwitching, '\\$Time(%.[^\\$]*)?\\$'))) then false() else true()")
    private static Violation ruleR74(SegmentTemplate segmentTemplate) {
        String bitstreamSwitching = segmentTemplate.getBitstreamSwitching();
        if (bitstreamSwitching != null && (bitstreamSwitching.contains("$Number") || bitstreamSwitching.contains("$Time"))) {
            return new Violation("R7.4", "Neither $Number$ nor the $Time$ identifier shall be included in the bitstreamSwitching attribute.");
        }
        return Violation.empty();
    }

    @ValidationRule("if (matches(@media, '\\$.[^\\$]*\\$')) " +
            "then every $y in (for $x in tokenize(@media, '\\$(Bandwidth|Time|Number|RepresentationID)(%.[^\\$]*)?\\$') " +
            "return matches($x, '\\$.[^\\$]*\\$')) satisfies $y eq false() else true()")
    private static Violation ruleR75(SegmentTemplate segmentTemplate) {
        if (segmentTemplate.getMedia() == null) {
            return Violation.empty();
        }
        Pattern pattern = Pattern.compile("\\$(.[^%$]*)(?:%[^$]*)?\\$");
        Matcher matcher = pattern.matcher(segmentTemplate.getMedia());

        List<String> validIdentifiers = Arrays.asList("Bandwidth", "Time", "Number", "RepresentationID");

        while (matcher.find()) {
            String identifier = matcher.group(1);
            if (!validIdentifiers.contains(identifier)) {
                return new Violation("R7.5", "Only identifiers such as $Bandwidth$, $Time$, $RepresentationID$, or $Number$ shall be used.");
            }
        }

        return Violation.empty();
    }

    @ValidationRule("if (matches(@media, '\\$RepresentationID%.[^\\$]*\\$')) then false() else true()")
    private static Violation ruleR76(SegmentTemplate segmentTemplate) {
        if (segmentTemplate.getMedia() != null && segmentTemplate.getMedia().matches(".*\\$RepresentationID%.[^$]*\\$.*")) {
            return new Violation("R7.6", "$RepresentationID$ shall not have a format tag.");
        }
        return Violation.empty();
    }

    public static List<Violation> validate(MPD mpd, SegmentTemplate segmentTemplate) {
        if (segmentTemplate == null)
            return Collections.emptyList();

        List<Violation> violations = new ArrayList<>();

        violations.addAll(Arrays.asList(
            ruleR70(segmentTemplate),
            ruleR71(segmentTemplate),
            ruleR72(segmentTemplate),
            ruleR73(segmentTemplate),
            ruleR74(segmentTemplate),
            ruleR75(segmentTemplate),
            ruleR76(segmentTemplate)));

        violations.addAll(SegmentTimelineValidator.validate(mpd, segmentTemplate.getTimescale(), segmentTemplate.getSegmentTimeline()));

        return violations;
    }
}

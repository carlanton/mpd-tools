package io.lindstrom.mpd.validator;

import io.lindstrom.mpd.data.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class PeriodValidator {
    /*
     *
     * R2.*: Check the conformance of Period
     *
     */

    @ValidationRule("if (string(@bitstreamSwitching) = 'true' and string(child::dash:AdaptationSet/@bitstreamSwitching) = 'false') then false() else true()")
    private static Violation ruleR20(Period period) {
        if (period.getBitstreamSwitching() != null && period.getBitstreamSwitching()) {
            for (AdaptationSet adaptationSet : period.getAdaptationSets()) {
                if (adaptationSet.getBitstreamSwitching() != null && !adaptationSet.getBitstreamSwitching()) {
                    return new Violation("R2.0", "If bitstreamSwitching is set to true all bitstreamSwitching declarations for AdaptationSet within this Period shall not be set to false.");
                }
            }
        }
        return Violation.empty();
    }

    @ValidationRule("if (@id = preceding::dash:Period/@id) then false() else true()")
    private static Violation ruleR21(MPD mpd) {
        if (!ValidationSupport.uniqueIds(mpd.getPeriods().stream().map(Period::getId).collect(Collectors.toList()))) {
            return new Violation("R2.1", "The id of each Period shall be unique.");
        }
        return Violation.empty();
    }

    // <!-- R2.2: This rule has been found to not work properly, hence disabled for now -->
    // <!-- assert test="if ((years-from-duration(@start) + months-from-duration(@start) + days-from-duration(@start) + hours-from-duration(@start) + minutes-from-duration(@start) +  seconds-from-duration(@start)) > (years-from-duration(following-sibling::dash:Period/@start) + months-from-duration(following-sibling::dash:Period/@start) + days-from-duration(following-sibling::dash:Period/@start) + hours-from-duration(following-sibling::dash:Period/@start) + minutes-from-duration(following-sibling::dash:Period/@start) +  seconds-from-duration(following-sibling::dash:Period/@start))) then false() else true()">Periods shall be physically ordered in the MPD file in increasing order of their start time.</assert-->

    @ValidationRule("if ((child::dash:SegmentBase and child::dash:SegmentTemplate and child::dash:SegmentList) or " +
            "(child::dash:SegmentBase and child::dash:SegmentTemplate) or " +
            "(child::dash:SegmentBase and child::dash:SegmentList) or " +
            "(child::dash:SegmentTemplate and child::dash:SegmentList)) then false() else true()")
    private static Violation ruleR23(Period period) {
        if (!ValidationSupport.atMostOneIsNonNull(period.getSegmentBase(), period.getSegmentList(), period.getSegmentTemplate())) {
            return new Violation("R2.3", "At most one of SegmentBase, SegmentTemplate and SegmentList shall be defined in Period.");

        }
        return Violation.empty();
    }

    @ValidationRule("if (not(@id) and ancestor::dash:MPD/@type = 'dynamic') then false() else true()")
    private static Violation ruleR24(Period period, MPD mpd) {
        if (period.getId() == null && mpd.getType() == PresentationType.DYNAMIC) {
            return new Violation("R2.4", "If the MPD is dynamic the Period element shall have an id.");
        }
        return Violation.empty();
    }

    @ValidationRule("if (not(descendant-or-self::dash:BaseURL) and not(descendant-or-self::dash:SegmentTemplate) and " +
            "not(descendant-or-self::dash:SegmentList) and not(@xlink:href = 'urn:mpeg:dash:resolve-to-zero:2013')) " +
            "then false() else true()")
    private static Violation ruleR251(Period period) {
        if (!period.getBaseURLs().isEmpty() || period.getSegmentTemplate() != null || period.getSegmentList() != null) {
            return Violation.empty();
        }

        for (AdaptationSet adaptationSet : period.getAdaptationSets()) {
            if (!adaptationSet.getBaseURLs().isEmpty() || adaptationSet.getSegmentTemplate() != null || adaptationSet.getSegmentList() != null) {
                return Violation.empty();
            }

            for (Representation representation : adaptationSet.getRepresentations()) {
                if (!representation.getBaseURLs().isEmpty() || representation.getSegmentTemplate() != null || representation.getSegmentList() != null) {
                    return Violation.empty();
                }
            }
        }

        return new Violation("R2.5.1", "At least one BaseURL, SegmentTemplate or SegmentList shall be defined in Period, AdaptationSet or Representation.");
    }

    @ValidationRule("if (@duration = 0 and count(child::dash:AdaptationSet)) then false() else true()")
    private static Violation ruleR252(Period period) {
        if (Duration.ZERO.equals(period.getDuration()) && period.getAdaptationSets().size() != 1) {
            return new Violation("R2.5.2", "If the duration attribute is set to zero, there should only be a single AdaptationSet present.");
        }
        return Violation.empty();
    }

    @ValidationRule("if (contains(ancestor::dash:MPD/@profiles, 'http://dashif.org/guidelines/dash') and dash:SegmentList) then false() else true()")
    private static Violation ruleRD20(Period period, MPD mpd) {
        if (mpd.getInteroperabilityPointsAndExtensions().contains("http://dashif.org/guidelines/dash") && period.getSegmentList() != null) {
            return new Violation("RD2.0", "DASH-IF IOP Section 3.2.2: \"the Period.SegmentList element shall not be present\" violated here");
        }
        return Violation.empty();
    }

    @ValidationRule("if (contains(ancestor::dash:MPD/@profiles, 'http://dashif.org/guidelines/dash') and " +
            "(count(child::dash:AdaptationSet[@contentType='video']) > 1) and (count(descendant::dash:Role[@value='main'])=0)) " +
            "then false() else true()")
    private static Violation ruleRD21(Period period, MPD mpd) {
        if (ValidationSupport.hasDashIOP(mpd)) {
            List<AdaptationSet> videoAdaptationSets = period.getAdaptationSets().stream()
                    .filter(adaptationSet -> "video".equals(adaptationSet.getContentType()))
                    .collect(Collectors.toList());

            if (videoAdaptationSets.size() > 1) {
                if (videoAdaptationSets.stream()
                        .flatMap(adaptationSet -> adaptationSet.getRoles().stream())
                        .filter(role -> role.getSchemeIdUri().equals("urn:mpeg:dash:role:2011"))
                        .noneMatch(role -> role.getValue().equals("main"))) {
                    return new Violation("RD2.1",
                            "DASH-IF IOP Section 3.2.2: \"If a Period contains multiple Adaptation Sets with value of the @contentType=\"video\" then at least one Adaptation Set shall contain a Role element <Role scheme=\"urn:mpeg:dash:role:2011\" value=\"main\">\" violated here");
                }

            }
        }
        return Violation.empty();
    }

    /*
     *
     * R17.*: Subset element
     *
     */

    @ValidationRule("if (@id = preceding::dash:Subset/@id) then false() else true()")
    private static Violation ruleR171(Period period) {
        HashSet<String> ids = new HashSet<>();
        period.getSubsets().forEach(subset -> ids.add(subset.getId()));
        if (ids.size() != period.getSubsets().size()) {
            return new Violation("R17.1", "The id of each Subset shall be unique.");

        }
        return Violation.empty();
    }

    public static List<Violation> validate(MPD mpd, Period period) {
        List<Violation> violations = new ArrayList<>();
        violations.addAll(Arrays.asList(
            ruleR20(period),
            ruleR21(mpd),
            ruleR23(period),
            ruleR24(period, mpd),
            ruleR251(period),
            ruleR252(period),
            ruleRD20(period, mpd),
            ruleRD21(period, mpd),
            ruleR171(period)));

        for (EventStream eventStream : period.getEventStreams()) {
            violations.addAll(EventStreamValidator.validate(eventStream));
        }

        if (period.getSegmentTemplate() != null) {
            violations.addAll(SegmentTemplateValidator.validate(mpd, period.getSegmentTemplate()));
        }

        if (period.getSegmentList() != null) {
            violations.addAll(SegmentListValidator.validate(mpd, period.getSegmentList()));
        }

        if (period.getSegmentBase() != null) {
            violations.addAll(SegmentBaseValidator.validate(mpd, period.getSegmentBase()));
        }

        for (AdaptationSet adaptationSet : period.getAdaptationSets()) {
            violations.addAll(AdaptationSetValidator.validate(mpd, period, adaptationSet));
        }

        for (Descriptor supplementalProperty : mpd.getSupplementalProperties()) {
            violations.addAll(SupplementalPropertyValidator.validate(period, supplementalProperty));
        }

        return violations;
    }
}

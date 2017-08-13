package io.lindstrom.mpd.validator;

import io.lindstrom.mpd.data.*;
import io.lindstrom.mpd.data.descriptor.Descriptor;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MPDValidator {
     /*
     *
     *  R1.*: Check the conformance of MPD
     *
     */

    @ValidationRule("if (@type = 'dynamic' and not(@availabilityStartTime)) then false() else true()")
    private static Violation ruleR10(MPD mpd) {
        // <!-- R1.0 -->
        // <assert test="if (@type = 'dynamic' and not(@availabilityStartTime)) then false() else true()">
        // If MPD is of type "dynamic" availabilityStartTime shall be defined.</assert>
        if (mpd.getType() == PresentationType.DYNAMIC && mpd.getAvailabilityStartTime() == null) {
            return new Violation("R1.0", "If MPD is of type \"dynamic\" availabilityStartTime shall be defined.");
        }
        return Violation.empty();
    }

    @ValidationRule("if (@type = 'dynamic' and not(@publishTime)) then false() else true()")
    private static Violation ruleR11(MPD mpd) {
        if (mpd.getType() == PresentationType.DYNAMIC && mpd.getPublishTime() == null) {
            return new Violation("R1.1", "If MPD is of type \"dynamic\" publishTime shall be defined.");
        }
        return Violation.empty();
    }

    @ValidationRule("if (@type = 'static' and @timeShiftBufferDepth and contains(@profiles, 'http://dashif.org/guidelines/dash')) then false() else true()")
    private static Violation ruleR12(MPD mpd) {
        if (mpd.getType() == PresentationType.STATIC && mpd.getTimeShiftBufferDepth() != null && ValidationSupport.hasDashIOP(mpd)) {
            return new Violation("R1.2", "If MPD is of type \"static\" and if the profile contains a DASH-IF IOP profile, then the timeShiftBufferDepth shall not be defined");
        }
        return Violation.empty();
    }

    // <!-- R1.3 -->
    // <!-- <assert test="if (@type = 'static' and not(@mediaPresentationDuration)) then false() else true()">If MPD is of type "static" mediaPresentationDuration shall be defined.</assert>  -->

    @ValidationRule("if (@type = 'static' and descendant::dash:Period[1]/@start and (years-from-duration(descendant::dash:Period[1]/@start) + months-from-duration(descendant::dash:Period[1]/@start) + days-from-duration(descendant::dash:Period[1]/@start) + hours-from-duration(descendant::dash:Period[1]/@start) + minutes-from-duration(descendant::dash:Period[1]/@start) +  seconds-from-duration(descendant::dash:Period[1]/@start)) > 0) then false() else true()")
    private static Violation ruleR14(MPD mpd) {
        if (mpd.getType() == PresentationType.STATIC && !mpd.getPeriods().isEmpty()) {
            Period firstPeriod = mpd.getPeriods().get(0);
            if (firstPeriod.getStart() != null && !firstPeriod.getStart().equals(Duration.ZERO)) {
                return new Violation("R1.4", "If MPD is of type \"static\" and the first period has a start attribute the start attribute shall be zero.");
            }
        }
        return Violation.empty();
    }

    @ValidationRule("if (not(@mediaPresentationDuration) and not(@minimumUpdatePeriod)) then false() else true()")
    private static Violation ruleR15(MPD mpd) {
        if (mpd.getMediaPresentationDuration() == null && mpd.getMinimumUpdatePeriod() == null) {
            return new Violation("R1.5", "If mediaPresentationDuration is not defined for the MPD minimumUpdatePeriod shall be defined or vice versa.");
        }
        return Violation.empty();
    }

    @ValidationRule("if (@type = 'static' and @minimumUpdatePeriod and contains(@profiles, 'http://dashif.org/guidelines/dash')) then false() else true()")
    private static Violation ruleR16(MPD mpd) {
        if (mpd.getType() == PresentationType.STATIC && mpd.getMinimumUpdatePeriod() != null && ValidationSupport.hasDashIOP(mpd)) {
            return new Violation("R1.6", "If MPD is of type \"static\" and if the profile contains a DASH-IF IOP profile, then the minimumUpdatePeriod shall not be defined.");
        }
        return Violation.empty();
    }

    @ValidationRule("if (not(@profiles) or " +
            "(contains(@profiles, 'urn:mpeg:dash:profile:isoff-on-demand:2011') or " +
            "contains(@profiles, 'urn:mpeg:dash:profile:isoff-live:2011') or " +
            "contains(@profiles, 'urn:mpeg:dash:profile:isoff-main:2011') or " +
            "contains(@profiles, 'urn:mpeg:dash:profile:full:2011') or " +
            "contains(@profiles, 'urn:mpeg:dash:profile:mp2t-main:2011') or " +
            "contains(@profiles, 'urn:mpeg:dash:profile:mp2t-simple:2011') or " +
            "contains (@profiles, 'http://dashif.org/guidelines/dashif#ac-4') or " +
            "contains (@profiles, 'http://dashif.org/guidelines/dashif#mha1'))) then true() else false()")
    private static Violation ruleR17(MPD mpd) {
        if (mpd.getProfiles().isEmpty()) {
            return new Violation("R1.7",
                    "The On-Demand profile shall be identified by the URN \"urn:mpeg:dash:profile:isoff-on-demand:2011\". " +
                            "The live profile shall be identified by the URN \"urn:mpeg:dash:profile:isoff-live:2011\". " +
                            "The main profile shall be identified by the URN \"urn:mpeg:dash:profile:isoff-main:2011\". " +
                            "The full profile shall be identified by the URN \"urn:mpeg:dash:profile:full:2011\". " +
                            "The mp2t-main profile shall be identified by the URN \"urn:mpeg:dash:profile:mp2t-main:2011\". " +
                            "The mp2t-simple profile shall be identified by the URN \"urn:mpeg:dash:profile:mp2t-simple:2011\"." +
                            "The Dolby AC-4 profile shall be identified by \"http://dashif.org/guidelines/dashif#ac-4\". " +
                            "The multichannel audio extension with MPEG-H 3D Audio profile shall be identified by \"http://dashif.org/guidelines/dashif#mha1\".");
        } else {
            return Violation.empty();
        }
    }

    @ValidationRule("if (not(contains(@profiles, 'urn:mpeg:dash:profile:isoff-on-demand:2011')) or not(@type) or @type='static') then true() else false()")
    private static Violation ruleR18(MPD mpd) {
        if (mpd.getProfiles().contains(Profile.MPEG_DASH_ON_DEMAND) && mpd.getType() != null && mpd.getType() != PresentationType.STATIC) {
            return new Violation("R1.8", "For On-Demand profile, the MPD @type shall be \"static\".");
        }
        return Violation.empty();
    }

    @ValidationRule("if (not(@mediaPresentationDuration) and not(@minimumUpdatePeriod) and not(dash:Period[last()]/@duration)) then false() else true()")
    private static Violation ruleR19(MPD mpd) {
        if (mpd.getMediaPresentationDuration() == null && mpd.getMinimumUpdatePeriod() == null) {
            List<Period> periods = mpd.getPeriods();
            if (periods.isEmpty() || periods.get(periods.size() - 1).getDuration() == null) {
                return new Violation("R1.9", "If minimumUpdatePeriod is not present and the last period does not include the duration attribute the mediaPresentationDuration must be present.");
            }
        }
        return Violation.empty();
    }

    // <!-- R1.10: Disabled, there is no such conformance point in DASH 2nd edition (cuurent) -->
    // <!-- assert test="if (@type='dynamic' and not(@id)) then false() else true()">If the MPD type is dynamic, the id shall be present </assert-->

    @ValidationRule("if (contains(ancestor::dash:MPD/@profiles, 'http://dashif.org/guidelines/dash') and " +
            "@type='dynamic' and " +
            "not(contains(@profiles, 'urn:mpeg:dash:profile:isoff-live:2011'))) then false() else true()")
    private static Violation ruleRD10(MPD mpd) {
        if (ValidationSupport.hasDashIOP(mpd) &&
                mpd.getType() == PresentationType.DYNAMIC &&
                !mpd.getProfiles().contains(Profile.MPEG_DASH_LIVE)) {
            return new Violation("RD1.0", "DASH-IF IOP Section 3.2.2.2: For dynamic MPD, the @profile shall include urn:mpeg:dash:profile:isoff-live:2011.");
        }
        return Violation.empty();
    }

    /*
     *
     * R11.*: Check the conformance of ProgramInformation
     *
     */

    @ValidationRule("if (count(parent::dash:MPD/dash:ProgramInformation) > 1 and not(@lang)) then false() else true()")
    private static Violation ruleR110(MPD mpd) {
        if (mpd.getProgramInformations().size() > 1) {
            for (ProgramInformation programInformation : mpd.getProgramInformations()) {
                if (programInformation.getLang() == null) {
                    return new Violation("R11.0", "If more than one ProgramInformation element is given each ProgramInformation element shall have a lang attribute.");
                }
            }
        }
        return Violation.empty();
    }

    /*
     *
     * R18.*: Check the conformance of UTCTiming
     *
     */
    @ValidationRule("if ((@schemeIdUri = 'urn:mpeg:dash:utc:ntp:2014') or (@schemeIdUri = 'urn:mpeg:dash:utc:sntp:2014') or " +
            "(@schemeIdUri = 'urn:mpeg:dash:utc:http-head:2014') or (@schemeIdUri = 'urn:mpeg:dash:utc:http-xsdate:2014') or " +
            "(@schemeIdUri = 'urn:mpeg:dash:utc:http-iso:2014') or (@schemeIdUri = 'urn:mpeg:dash:utc:http-ntp:2014') or " +
            "(@schemeIdUri = 'urn:mpeg:dash:utc:direct:2014')) then true() else false()")
    private static Violation ruleR181(UTCTiming utcTiming) {
        // We are using an enum here, so this test is not needed.
        return Violation.empty();
    }

    public static List<Violation> validate(MPD mpd) {
        List<Violation> violations = new ArrayList<>();
        violations.addAll(Arrays.asList(
            ruleR10(mpd),
            ruleR11(mpd),
            ruleR12(mpd),
            ruleR14(mpd),
            ruleR15(mpd),
            ruleR16(mpd),
            ruleR17(mpd),
            ruleR18(mpd),
            ruleR19(mpd),
            ruleRD10(mpd),
            ruleR110(mpd)));

        for (Period period : mpd.getPeriods()) {
            violations.addAll(PeriodValidator.validate(mpd, period));
        }

        for (UTCTiming utcTiming : mpd.getUtcTimings()) {
            violations.add(ruleR181(utcTiming));
        }

        for (Descriptor descriptor : mpd.getSupplementalProperties()) {
            violations.addAll(SupplementalPropertyValidator.validate(mpd, descriptor));
        }

        return violations.stream()
                .filter(v -> !v.equals(Violation.empty()))
                .distinct()
                .collect(Collectors.toList());
    }
}

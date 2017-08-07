package io.lindstrom.mpd.validator;

import io.lindstrom.mpd.data.AdaptationSet;
import io.lindstrom.mpd.data.Descriptor;
import io.lindstrom.mpd.data.RepresentationBase;

import java.util.Arrays;
import java.util.List;

public class ContentProtectionValidator {

    /*
     *
     * R12.*: Check the conformance of ContentProtection
     *
     */
    @ValidationRule("if ((@schemeIdUri = 'urn:mpeg:dash:mp4protection:2011') and not(string-length(@value) = 4)) then false() else true()")
    private static Violation ruleR120(Descriptor contentProtection) {
        if ("urn:mpeg:dash:mp4protection:2011".equals(contentProtection.getSchemeIdUri()) &&
                (contentProtection.getValue() == null || contentProtection.getValue().length() != 4)) {
            return new Violation("R12.0", "The value of ContentProtection shall be the 4CC contained in the Scheme Type Box");
        }

        return Violation.empty();
    }

    @ValidationRule("if ((@schemeIdUri = 'urn:mpeg:dash:13818:1:CA_descriptor:2011') and not(string-length(@value) = 4)) then false() else true()")
    private static Violation ruleR121(Descriptor contentProtection) {
        if ("urn:mpeg:dash:13818:1:CA_descriptor:2011".equals(contentProtection.getSchemeIdUri()) &&
                (contentProtection.getValue() == null || contentProtection.getValue().length() != 4)) {
            return new Violation("R12.1", "The value of ContentProtection shall be the 4-digit lower-case hexadecimal Representation.");
        }
        
        return Violation.empty();
    }

    @ValidationRule("if (not(parent::dash:AdaptationSet)) then false() else true()")
    private static <T extends RepresentationBase> Violation ruleR122(T contentProtectionParent) {
        if (!(contentProtectionParent instanceof AdaptationSet)) {
            return new Violation("R12.2",
                    "The ContentProtection descriptors shall always be present in the AdaptationSet element and apply to all contained Representations.");
        }

        return Violation.empty();
    }

    @ValidationRule("if ((@schemeIdUri = 'urn:mpeg:dash:mp4protection:2011') and (@value= 'cenc') " +
            "and not(parent::dash:AdaptationSet)) then false() else true()")
    private static <T extends RepresentationBase> Violation ruleR123(T contentProtectionParent, Descriptor contentProtection) {
        if ("urn:mpeg:dash:mp4protection:2011".equals(contentProtection.getSchemeIdUri()) &&
                "cenc".equals(contentProtection.getValue()) && !(contentProtectionParent instanceof AdaptationSet)) {
            return new Violation("R12.3",
                    "The ContentProtection descriptor for the mp4 protection scheme with @schemeIdUri 'urn:mpeg:dash:mp4protection:2011' and @value 'cenc' shall be present in the AdaptationSet element.");
        }

        return Violation.empty();
    }

    public static <T extends RepresentationBase> List<Violation> validate(T contentProtectionParent, Descriptor contentProtection) {
        return Arrays.asList(
                ruleR120(contentProtection),
                ruleR121(contentProtection),
                ruleR122(contentProtectionParent),
                ruleR123(contentProtectionParent, contentProtection)
        );
    }
}

package io.lindstrom.mpd.validator.rules;

import io.lindstrom.mpd.data.descriptor.Descriptor;
import io.lindstrom.mpd.data.MPD;

import java.util.Arrays;
import java.util.List;

public class SupplementalPropertyValidator {
    /*
     *
     * R19.*: Check the conformance of SupplementalProperty
     *
     */

    @ValidationRule("if((@schemeIdUri= 'urn:mpeg:dash:chaining:2016') and " +
            "not((count(tokenize(@value, ','))=1) or (count(tokenize(@value, ','))>1)) )then false() else true()")
    private static Violation ruleR191(Descriptor supplementalProperty) {
        if ("urn:mpeg:dash:chaining:2016".equals(supplementalProperty.getSchemeIdUri()) &&
                (supplementalProperty.getValue() == null || supplementalProperty.getValue().split(",").length >= 1)) {
            return new Violation("R19.1", "If schemeIdUri urn:mpeg:dash:chaining:2016 is used, " +
                    "then value attribute shall be composed of the comma separated parameters " +
                    "(no comma needed if only first parameter is present).");
        }
        return Violation.empty();
    }

    @ValidationRule("if(not(parent::dash:MPD) and (@schemeIdUri= 'urn:mpeg:dash:fallback:2016') )then false() else true()")
    private static Violation ruleR192(Object parent, Descriptor supplementalProperty) {
        if ("urn:mpeg:dash:fallback:2016".equals(supplementalProperty.getSchemeIdUri()) && !(parent instanceof MPD)) {
            return new Violation("R19.2", "MPD fallback chaining shall be signaled by Supplemental " +
                    "Descriptor on MPD level with schemeIdUri urn:mpeg:dash:fallback:2016.");
        }
        return Violation.empty();
    }

    @ValidationRule("if((@schemeIdUri= 'urn:mpeg:dash:fallback:2016') and not((count(tokenize(@value, ' '))=1) or " +
            "(count(tokenize(@value, ' '))>1)) )then false() else true()")
    private static Violation ruleR193(Descriptor supplementalProperty) {
        if ("urn:mpeg:dash:fallback:2016".equals(supplementalProperty.getSchemeIdUri()) &&
                (supplementalProperty.getValue() == null || supplementalProperty.getValue().split(" ").length >= 1)) {
            return new Violation("R19.3", "If schemeIdUri urn:mpeg:dash:fallback:2016 is used, " +
                    "then value attribute shall be composed of one URL or whitespace separated URLs.");
        }
        return Violation.empty();
    }

    public static List<Violation> validate(Object parent, Descriptor supplementalProperty) {
        return Arrays.asList(
                ruleR191(supplementalProperty),
                ruleR192(parent, supplementalProperty),
                ruleR193(supplementalProperty));
    }
}

package io.lindstrom.mpd.validator;

import io.lindstrom.mpd.data.descriptor.Descriptor;

import java.util.Arrays;
import java.util.List;


public class RoleValidator {
    /*
     *
     * R13.*: Check the conformance of Role
     *
     */

    private static final List<String> VALID_ROLES = Arrays.asList(
            "caption",
            "subtitle",
            "main",
            "alternate",
            "supplementary",
            "commentary",
            "dub"
    );

    @ValidationRule("if ((@schemeIdUri = 'urn:mpeg:dash:role:2011') and " +
            "not(@value = 'caption' or @value = 'subtitle' or @value = 'main' or " +
            "@value = 'alternate' or @value = 'supplementary' or @value = 'commentary' or @value = 'dub')) then false else true()")
    private static Violation ruleR130(Descriptor role) {
        if ("urn:mpeg:dash:role:2011".equals(role.getSchemeIdUri()) && !VALID_ROLES.contains(role.getValue())) {
            return new Violation("R13.0", "The value of Role (role) shall be caption, subtitle, main, alternate, supplementary, commentary or dub.");
        }
        return Violation.empty();
    }

    @ValidationRule("if ((@schemeIdUri = 'urn:mpeg:dash:stereoid:2011') and " +
            "not(starts-with(@value, 'l') or starts-with(@value, 'r'))) then false() else true()")
    private static Violation ruleR131(Descriptor role) {
        if ("urn:mpeg:dash:stereoid:2011".equals(role.getSchemeIdUri()) &&
                !(role.getValue().startsWith("l") || role.getValue().startsWith("r"))) {
            return new Violation("R13.1", "The value of Role (stereoid) shall start with 'l' or 'r'.");
        }
        return Violation.empty();
    }

    public static List<Violation> validate(Descriptor role) {
        return Arrays.asList(ruleR130(role), ruleR131(role));
    }
}

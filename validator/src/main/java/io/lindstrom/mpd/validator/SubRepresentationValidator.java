package io.lindstrom.mpd.validator;

import io.lindstrom.mpd.data.Descriptor;
import io.lindstrom.mpd.data.MPD;
import io.lindstrom.mpd.data.SubRepresentation;

import java.util.ArrayList;
import java.util.List;

public class SubRepresentationValidator {
    /*
     *
     * R6.*: Check the conformance of SubRepresentation
     *
     */

    @ValidationRule("if (@level and not(@bandwidth)) then false() else true()")
    private static Violation ruleR60(SubRepresentation subRepresentation) {
        if (subRepresentation.getLevel() != null && subRepresentation.getBandwidth() == null) {
            return new Violation("R6.0",
                    "If the level attribute is defined for a SubRepresentation also the bandwidth attribute shall be defined.");
        }
        return Violation.empty();
    }

    public static List<Violation> validate(MPD mpd, SubRepresentation subRepresentation) {
        List<Violation> violations = new ArrayList<>();
        violations.add(ruleR60(subRepresentation));

        for (Descriptor contentProtection : subRepresentation.getContentProtections()) {
            violations.addAll(ContentProtectionValidator.validate(subRepresentation, contentProtection));
        }

        for (Descriptor audioChannelConfiguration : subRepresentation.getAudioChannelConfigurations()) {
            violations.addAll(AudioChannelConfigurationValidator.validate(mpd, audioChannelConfiguration));
        }

        for (Descriptor descriptor : mpd.getSupplementalProperties()) {
            violations.addAll(SupplementalPropertyValidator.validate(subRepresentation, descriptor));
        }

        return violations;
    }
}

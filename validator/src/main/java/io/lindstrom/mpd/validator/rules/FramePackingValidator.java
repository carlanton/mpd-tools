package io.lindstrom.mpd.validator.rules;

import io.lindstrom.mpd.data.AdaptationSet;
import io.lindstrom.mpd.data.descriptor.Descriptor;
import io.lindstrom.mpd.data.Representation;

import java.util.Arrays;
import java.util.List;

public class FramePackingValidator {
    /*
     *
     * R14.*: Check the conformance of FramePacking
     *
     */

    @ValidationRule("if ((@schemeIdUri = 'urn:mpeg:dash:14496:10:frame_packing_arrangement_type:2011') and " +
            "not(contains(parent::dash:AdaptationSet/@codecs, 'avc') or " +
            "contains(parent::dash:AdaptationSet/@codecs, 'svc') or " +
            "contains(parent::dash:AdaptationSet/@codecs, 'mvc')) and " +
            "not(contains(parent::dash:Representation/@codecs, 'avc') or " +
            "contains(parent::dash:Representation/@codecs, 'svc') or " +
            "contains(parent::dash:Representation/@codecs, 'mvc'))) " +
            "then false() else true()")
    private static Violation ruleR140(AdaptationSet adaptationSet, Representation representation, Descriptor framePacking) {
        if (!"urn:mpeg:dash:14496:10:frame_packing_arrangement_type:2011".equals(framePacking.getSchemeIdUri())) {
            return Violation.empty();
        }

        String adaptationSetCodecs = adaptationSet.getCodecs() == null ? "" : adaptationSet.getCodecs();
        String representationCodecs = representation.getCodecs() == null ? "" : representation.getCodecs();

        if (!(adaptationSetCodecs.contains("avc") || adaptationSetCodecs.contains("svc") || adaptationSetCodecs.contains("mvc")) &&
                !(representationCodecs.contains("avc") || representationCodecs.contains("svc") || representationCodecs.contains("mvc"))) {
            return new Violation("R14.0",
                    "The URI urn:mpeg:dash:14496:10:frame_packing_arrangement_type:2011 is used for Adaptation Sets or " +
                            "Representations that contain a video component that conforms to ISO/IEC 14496-10.");
        }

        return Violation.empty();
    }

    @ValidationRule("if ((@schemeIdUri = 'urn:mpeg:dash:13818:1:stereo_video_format_type:2011') and " +
            "not(parent::dash:AdaptationSet/@mimeType = 'video/mp2t') and " +
            "not(parent::dash:Representation/@mimeType = 'video/mp2t')) then false() else true()")
    private static Violation ruleR141(AdaptationSet adaptationSet, Representation representation, Descriptor framePacking) {
        if (!"urn:mpeg:dash:13818:1:stereo_video_format_type:2011".equals(framePacking.getSchemeIdUri())) {
            return Violation.empty();
        }

        String adaptationSetMimeType = adaptationSet.getMimeType() == null ? "" : adaptationSet.getMimeType();
        String representationMimeType = representation.getMimeType() == null ? "" : representation.getMimeType();

        if (!"video/mp2t".equals(adaptationSetMimeType) && !"video/mp2t".equals(representationMimeType)) {
            return new Violation("R14.1", "The URI urn:mpeg:dash:13818:1:stereo_video_format_type:2011 is used " +
                    "for Adaptation Sets or Representations that contain a video component that conforms to ISO/IEC 13818-1.");
        }

        return Violation.empty();
    }

    @ValidationRule("if (not(@schemeIdUri = 'urn:mpeg:dash:14496:10:frame_packing_arrangement_type:2011') and " +
            "not(@schemeIdUri = 'urn:mpeg:dash:13818:1:stereo_video_format_type:2011')) then false() else true()")
    private static Violation ruleR142(Descriptor framePacking) {
        if (!"urn:mpeg:dash:14496:10:frame_packing_arrangement_type:2011".equals(framePacking.getSchemeIdUri()) &&
            !"urn:mpeg:dash:13818:1:stereo_video_format_type:2011".equals(framePacking.getSchemeIdUri())) {
            return new Violation("R14.2", "schemeIdUri for FramePacking descriptor shall be " +
                    "urn:mpeg:dash:14496:10:frame_packing_arrangement_type:2011 or urn:mpeg:dash:13818:1:stereo_video_format_type:2011.");
        }
        return Violation.empty();
    }

    @ValidationRule("if (not(@value = '0' or @value = '1' or @value = '2' or @value = '3' or @value = '4' or " +
            "@value = '5' or @value = '6')) then false() else true()")
    private static Violation ruleR143(Descriptor framePacking) {
        List<String> validValues = Arrays.asList("0", "1", "2", "3", "4", "5", "6");

        if (!validValues.contains(framePacking.getValue())) {
            return new Violation("14.3", "The value of FramePacking shall be 0 to 6 as defined in ISO/IEC 23001-8.");
        }
        return Violation.empty();
    }

    public static List<Violation> validate(AdaptationSet adaptationSet, Representation representation, Descriptor framePacking) {
        return Arrays.asList(
            ruleR140(adaptationSet, representation, framePacking),
            ruleR141(adaptationSet, representation, framePacking),
            ruleR142(framePacking),
            ruleR143(framePacking));
    }
}

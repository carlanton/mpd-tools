package io.lindstrom.mpd.validator;

import io.lindstrom.mpd.data.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RepresentationValidator {
    /*
     *
     * R5.*: Check the conformance of Representation
     *
     */

    @ValidationRule("if (not(@mimeType) and not(parent::dash:AdaptationSet/@mimeType)) then false() else true()")
    private static Violation ruleR501(AdaptationSet adaptationSet, Representation representation) {
        if (representation.getMimeType() == null && adaptationSet.getMimeType() == null) {
            return new Violation("R5.0.1", "Either the Representation or the containing AdaptationSet shall have the mimeType attribute.");
        }
        return Violation.empty();
    }

    @ValidationRule("if (@mimeType and following-sibling::dash:Representation/@mimeType and " +
            "not(following-sibling::dash:Representation/@mimeType = @mimeType)) then false() else true()")
    private static Violation ruleR502(AdaptationSet adaptationSet, Representation representation) {
        for (Representation otherRepresentation : adaptationSet.getRepresentations()) {
            if (otherRepresentation.getMimeType() == null && representation.getMimeType() == null) {
                continue;
            }

            if (representation.getMimeType() == null || !representation.getMimeType().equals(otherRepresentation.getMimeType())) {
                return new Violation("R5.0.2", "DASH-IF IOP (v3.3), Section 3.2.13 : " +
                        "\"In contrast to MPEG-DASH which does not prohibit the use of multiplexed Representations, " +
                        "in the DASH-IF IOPs one Adaptation Set always contains exactly a single media type.\".");
            }
        }
        return Violation.empty();
    }

    @ValidationRule("if (not(" +
            "child::dash:SegmentTemplate or parent::dash:AdaptationSet/dash:SegmentTemplate or ancestor::dash:Period/dash:SegmentTemplate) and " +
            "(contains(@profiles, 'urn:mpeg:dash:profile:isoff-live:2011') or " +
            "contains(ancestor::dash:MPD/@profiles, 'urn:mpeg:dash:profile:isoff-live:2011') or " +
            "contains(parent::dash:AdaptationSet/@profiles, 'urn:mpeg:dash:profile:isoff-live:2011'))" +
            ") then false() else true()")
    private static Violation ruleR51(MPD mpd, Period period, AdaptationSet adaptationSet, Representation representation) {
        if (!mpd.getProfiles().contains(Profile.MPEG_DASH_LIVE)) {
            return Violation.empty();
        }

        // NOTE: Not really the same as the validation rule (we're not checking parent::dash:AdaptationSet/@profiles)
        if (period.getSegmentTemplate() == null && adaptationSet.getSegmentTemplate() == null && representation.getSegmentTemplate() == null) {
            return new Violation("R5.1",
                    "For live profile, the SegmentTemplate element shall be present on at least one of the three levels, " +
                    "the Period level containing the Representation, " +
                    "the Adaptation Set containing the Representation, " +
                    "or on Representation level itself.");
        }
        return Violation.empty();
    }


    @ValidationRule("if ((child::dash:SegmentBase and child::dash:SegmentTemplate and child::dash:SegmentList) or " +
            "(child::dash:SegmentBase and child::dash:SegmentTemplate) or " +
            "(child::dash:SegmentBase and child::dash:SegmentList) or " +
            "(child::dash:SegmentTemplate and child::dash:SegmentList)) then false() else true()")
    private static Violation ruleR52(Representation representation) {
        if (!ValidationSupport.atMostOneIsNonNull(
                representation.getSegmentBase(),
                representation.getSegmentTemplate(),
                representation.getSegmentList())) {
            return new Violation("R5.2",
                    "At most one of SegmentBase, SegmentTemplate and SegmentList shall be defined in Representation.");
        }
        return Violation.empty();
    }


    @ValidationRule("if (contains(ancestor::dash:MPD/@profiles, 'http://dashif.org/guidelines/dash') and " +
            "parent::dash:AdaptationSet/@contentType='video' and " +
            "(((@width != preceding-sibling::dash:Representation/@width) and " +
            "not(parent::dash:AdaptationSet/@maxWidth)) or " +
            "((@height != preceding-sibling::dash:Representation/@height) and " +
            "not(parent::dash:AdaptationSet/@maxHeight)) or " +
            "((@frameRate != preceding-sibling::dash:Representation/@frameRate) and " +
            "not(parent::dash:AdaptationSet/@maxFrameRate)))) then false() else true()")
    private static Violation ruleRD50(MPD mpd, AdaptationSet adaptationSet) {
        if (!ValidationSupport.hasDashIOP(mpd) || !"video".equals(adaptationSet.getContentType())) {
            return Violation.empty();
        }

        if (adaptationSet.getMaxWidth() == null && adaptationSet.getRepresentations().stream().map(RepresentationBase::getWidth).distinct().count() != 1 ||
                adaptationSet.getMaxHeight() == null && adaptationSet.getRepresentations().stream().map(RepresentationBase::getHeight).distinct().count() != 1 ||
                adaptationSet.getFrameRate() == null && adaptationSet.getRepresentations().stream().map(RepresentationBase::getFrameRate).distinct().count() != 1) {
            return new Violation("RD5.0", "DASH-IF IOP Section 3.2.4: \"" +
                    "For any Adaptation Sets with value of the @contentType=\"video\" the following attributes shall be present: " +
                    "@maxWidth (or @width if all Representations have the same width), " +
                    "@maxHeight (or @height if all Representations have the same width), " +
                    "@maxFrameRate (or @frameRate if all Representations have the same width)\" violated here");
        }

        return Violation.empty();
    }

    @ValidationRule("if (contains(ancestor::dash:MPD/@profiles, 'http://dashif.org/guidelines/dash') and " +
            "parent::dash:AdaptationSet/@contentType='video' and " +
            "((not(@width) and not(parent::dash:AdaptationSet/@width)) or " +
            "(not(@height) and not(parent::dash:AdaptationSet/@height)) or " +
            "(not(@frameRate) and not(parent::dash:AdaptationSet/@frameRate)) or " +
            "not(@sar))) " +
            "then false() else true()")
    private static Violation ruleRD51(MPD mpd, AdaptationSet adaptationSet, Representation representation) {
        if (!ValidationSupport.hasDashIOP(mpd) || !"video".equals(adaptationSet.getContentType())) {
            return Violation.empty();
        }

        if (representation.getWidth() == null && adaptationSet.getWidth() == null ||
                representation.getHeight() == null && adaptationSet.getHeight() == null ||
                representation.getFrameRate() == null && adaptationSet.getFrameRate() == null ||
                representation.getSar() == null) {

            return new Violation("RD5.1", "DASH-IF IOP Section 3.2.4: \"" +
                    "For any Representation within an Adaptation Set with value of the @contentType=\"video\" the " +
                    "following attributes shall be present: " +
                    "@width, if not present in AdaptationSet element; " +
                    "@height, if not present in AdaptationSet element; " +
                    "@frameRate, if not present in AdaptationSet element;" +
                    "@sar\" violated here");
        }
        return Violation.empty();

    }

    @ValidationRule("if (contains(ancestor::dash:MPD/@profiles, 'http://dashif.org/guidelines/dash') and " +
            "parent::dash:AdaptationSet/@contentType='video' and " +
            "(@scanType and not(@scanType='progressive')))then false() else true()")
    private static Violation ruleRD52(MPD mpd, AdaptationSet adaptationSet, Representation representation) {
        if (!ValidationSupport.hasDashIOP(mpd) || !"video".equals(adaptationSet.getContentType())) {
            return Violation.empty();
        }

        if (representation.getScanType() != null && representation.getScanType() != VideoScanType.PROGRESSIVE) {
            return new Violation("RD5.2", "DASH-IF IOP Section 3.2.4: \"" +
                    "For Adaptation Set or for any Representation within an Adaptation Set with value of the " +
                    "@contentType=\"video\" the attribute @scanType shall either not be present or shall be set to " +
                    "'progressive' \", violated here");
        }
        return Violation.empty();
    }

    @ValidationRule("if (contains(ancestor::dash:MPD/@profiles, 'http://dashif.org/guidelines/dash') and " +
            "parent::dash:AdaptationSet/@contentType='audio' and " +
            "((not(@audioSamplingRate) and not(parent::dash:AdaptationSet/@audioSamplingRate)) or " +
            "(not(dash:AudioChannelConfiguration) and not(parent::dash:AdaptationSet/dash:AudioChannelConfiguration)))) " +
            "then false() else true()")
    private static Violation ruleRD53(MPD mpd, AdaptationSet adaptationSet, Representation representation) {
        if (!ValidationSupport.hasDashIOP(mpd) || !"audio".equals(adaptationSet.getContentType())) {
            return Violation.empty();
        }

        if ((representation.getAudioSamplingRate() == null && adaptationSet.getAudioSamplingRate() == null) ||
                representation.getAudioChannelConfigurations().isEmpty() && adaptationSet.getAudioChannelConfigurations().isEmpty()) {
            return new Violation("RD5.3", "DASH-IF IOP Section 3.2.4: \"" +
                    "For any Representation within an Adaptation Set with value of the @contentType=\"audio\" the following " +
                    "elements and attributes shall be present: " +
                    "@audioSamplingRate, if not present in AdaptationSet element; " +
                    "AudioChannelConfiguration, if not present in AdaptationSet element\" violated here");
        }
        return Violation.empty();
    }

    @ValidationRule("if (contains(ancestor::dash:MPD/@profiles, 'http://dashif.org/guidelines/dash') and " +
            "((count(tokenize(@codecs, ',')) > 1) or " +
            "(count(tokenize(parent::dash:AdaptationSet/@codecs, ',')) > 1))) then false() else true()")
    private static Violation ruleRD54(MPD mpd, AdaptationSet adaptationSet, Representation representation) {
        if (!ValidationSupport.hasDashIOP(mpd)) {
            return Violation.empty();
        }

        if (representation.getCodecs() != null && representation.getCodecs().split(",").length > 1 ||
                adaptationSet.getCodecs() != null && adaptationSet.getCodecs().split(",").length > 1) {
            return new Violation("RD5.4", "If profiles contain dash-if profile identifier, \"codecs\" " +
                    "attribute on AdaptationSet level OR Representation level shall not contain more than one " +
                    "identifiers as a comma separated list");
        }
        return Violation.empty();
    }

    public static List<Violation> validate(MPD mpd, Period period, AdaptationSet adaptationSet, Representation representation) {
        List<Violation> violations = new ArrayList<>();

        violations.addAll(Arrays.asList(
            ruleR501(adaptationSet, representation),
            ruleR502(adaptationSet, representation),
            ruleR51(mpd, period, adaptationSet, representation),
            ruleR52(representation),
            ruleRD50(mpd, adaptationSet),
            ruleRD51(mpd, adaptationSet, representation),
            ruleRD52(mpd, adaptationSet, representation),
            ruleRD53(mpd, adaptationSet, representation),
            ruleRD54(mpd, adaptationSet, representation)
        ));

        if (representation.getSegmentTemplate() != null) {
            violations.addAll(SegmentTemplateValidator.validate(mpd, representation.getSegmentTemplate()));
        }

        if (representation.getSegmentList() != null) {
            violations.addAll(SegmentListValidator.validate(mpd, representation.getSegmentList()));
        }

        if (representation.getSegmentBase() != null) {
            violations.addAll(SegmentBaseValidator.validate(mpd, representation.getSegmentBase()));
        }

        for (Descriptor framePacking : representation.getFramePackings()) {
            violations.addAll(FramePackingValidator.validate(adaptationSet, representation, framePacking));
        }

        for (Descriptor contentProtection : representation.getContentProtections()) {
            violations.addAll(ContentProtectionValidator.validate(representation, contentProtection));
        }

        for (Descriptor audioChannelConfiguration : representation.getAudioChannelConfigurations()) {
            violations.addAll(AudioChannelConfigurationValidator.validate(mpd, audioChannelConfiguration));
        }

        for (Descriptor supplementalProperty : representation.getSupplementalProperties()) {
            violations.addAll(SupplementalPropertyValidator.validate(representation, supplementalProperty));
        }

        for (SubRepresentation descriptor : representation.getSubRepresentations()) {
            violations.addAll(SubRepresentationValidator.validate(mpd, descriptor));
        }

        return violations;
    }
}

package io.lindstrom.mpd.validator.rules;

import io.lindstrom.mpd.data.*;
import io.lindstrom.mpd.data.descriptor.Descriptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class AdaptationSetValidator {
    /*
     *
     * R3.*: Check the conformance of AdaptationSet
     *
     */

    @ValidationRule("if (@id = preceding-sibling::dash:AdaptationSet/@id) then false() else true()")
    private static Violation ruleR30(Period period) {
        if (!ValidationSupport.uniqueIds(period.getAdaptationSets().stream().map(AdaptationSet::getId).collect(Collectors.toList()))) {
            return new Violation("R3.0", "The id of each AdaptationSet within a Period shall be unique.");
        }
        return Violation.empty();
    }

    @ValidationRule("if ((descendant::dash:SupplementalProperty/@value = following-sibling::dash:AdaptationSet/@id) " +
            "and (@segmentAlignment='true') and " +
            "(following-sibling::dash:AdaptationSet/@segmentAlignment = 'false')) then false() else true()")
    private static Violation ruleRx38(Period period) {
        // <!-- DASH-IF v 3.3 Section 3.8  -->
        // <assert test="if ((descendant::dash:SupplementalProperty/@value = following-sibling::dash:AdaptationSet/@id) and (@segmentAlignment='true') and (following-sibling::dash:AdaptationSet/@segmentAlignment = 'false')) then false() else true()">
        //
        // If the content author signals the ability of Adaptation Set switching and as @segmentAlignment or
        // @subsegmentAlignment are set to TRUE for one Adaptation Set, the (Sub)Segment alignment shall hold for
        // all Representations in all Adaptation Sets for which the @id value is included in the @value attribute of
        // the Supplemental descriptor.
        //
        // </assert>

        //return new Violation("Rx38", "Not implemented!");
        // TODO
        return Violation.empty();
    }

    @ValidationRule("if ((@lang = descendant::dash:ContentComponent/@lang) or " +
            "(@contentType = descendant::dash:ContentComponent/@contentType) or " +
            "(@par = descendant::dash:ContentComponent/@par)) then false() else true()")
    private static Violation ruleR31(AdaptationSet adaptationSet) {
        boolean valid = true;

        if (adaptationSet.getLang() != null && adaptationSet.getContentComponents().stream().anyMatch(contentComponent ->
                adaptationSet.getLang().equals(contentComponent.getLang()))) {
            valid = false;
        }

        if (adaptationSet.getContentType() != null && adaptationSet.getContentComponents().stream().anyMatch(contentComponent ->
                adaptationSet.getContentType().equals(contentComponent.getContentType()))) {
            valid = false;
        }

        if (adaptationSet.getPar() != null && adaptationSet.getContentComponents().stream().anyMatch(contentComponent ->
                adaptationSet.getPar().equals(contentComponent.getPar()))) {
            valid = false;
        }

        if (!valid) {
            return new Violation("R3.1", "Attributes from the AdaptationSet shall not be repeated in the descendanding ContentComponent elements.");
        }

        return Violation.empty();
    }

    @ValidationRule("if (" +
            "(@profiles and descendant::dash:Representation/@profiles) or " +
            "(@width and descendant::dash:Representation/@width) or " +
            "(@height and descendant::dash:Representation/@height) or " +
            "(@sar and descendant::dash:Representation/@sar) or " +
            "(@frameRate and descendant::dash:Representation/@frameRate) or " +
            "(@audioSamplingRate and descendant::dash:Representation/@audioSamplingRate) or " +
            "(@mimeType and descendant::dash:Representation/@mimeType) or " +
            "(@segmentProfiles and descendant::dash:Representation/@segmentProfiles) or " +
            "(@codecs and descendant::dash:Representation/@codecs) or " +
            "(@maximumSAPPeriod and descendant::dash:Representation/@maximumSAPPeriod) or " +
            "(@startWithSAP and descendant::dash:Representation/@startWithSAP) or " +
            "(@maxPlayoutRate and descendant::dash:Representation/@maxPlayoutRate) or " +
            "(@codingDependency and descendant::dash:Representation/@codingDependency) or " +
            "(@scanType and descendant::dash:Representation/@scanType)" +
            ") then false() else true()")
    private static Violation ruleR32(AdaptationSet adaptationSet) {
        for (Representation representation : adaptationSet.getRepresentations()) {
            if ((adaptationSet.getProfiles() != null && representation.getProfiles() != null) ||
                    (adaptationSet.getWidth() != null && representation.getWidth() != null) ||
                    (adaptationSet.getHeight() != null && representation.getHeight() != null) ||
                    (adaptationSet.getSar() != null && representation.getSar() != null) ||
                    (adaptationSet.getFrameRate() != null && representation.getFrameRate() != null) ||
                    (adaptationSet.getAudioSamplingRate() != null && representation.getAudioSamplingRate() != null) ||
                    (adaptationSet.getMimeType() != null && representation.getMimeType() != null) ||
                    (adaptationSet.getSegmentProfiles() != null && representation.getSegmentProfiles() != null) ||
                    (adaptationSet.getCodecs() != null && representation.getCodecs() != null) ||
                    (adaptationSet.getMaximumSAPPeriod() != null && representation.getMaximumSAPPeriod() != null) ||
                    (adaptationSet.getStartWithSAP() != null && representation.getStartWithSAP() != null) ||
                    (adaptationSet.getMaxPlayoutRate() != null && representation.getMaxPlayoutRate() != null) ||
                    (adaptationSet.getCodingDependency() != null && representation.getCodingDependency() != null) ||
                    (adaptationSet.getScanType() != null && representation.getScanType() != null)) {
                return new Violation("R3.2", "Common attributes for AdaptationSet and Representation shall either be in one of the elements but not in both.");
            }
        }
        return Violation.empty();
    }

    private static boolean ruleR33Helper(Long min, Long max) {
        return min != null && max != null && min > max;
    }

    @ValidationRule("if ((@minWidth > @maxWidth) or (@minHeight > @maxHeight) or (@minBandwidth > @maxBandwidth)) then false() else true()")
    private static Violation ruleR33(AdaptationSet adaptationSet) {
        if (ruleR33Helper(adaptationSet.getMinWidth(), adaptationSet.getMaxWidth()) ||
                ruleR33Helper(adaptationSet.getMinHeight(), adaptationSet.getMaxHeight()) ||
                ruleR33Helper(adaptationSet.getMinBandwidth(), adaptationSet.getMaxBandwidth())) {
            return new Violation("R3.3", "Each minimum value (minWidth, minHeight, minBandwidth) shall be larger than the maximum value.");
        }
        return Violation.empty();
    }

    @ValidationRule("if (descendant::dash:Representation/@bandwidth < @minBandwidth or " +
            "descendant::dash:Representation/@bandwidth > @maxBandwidth) then false() else true()")
    private static Violation ruleR34(AdaptationSet adaptationSet) {
        for (Representation representation : adaptationSet.getRepresentations()) {
            if (!ValidationSupport.rangeCheck(adaptationSet.getMinBandwidth(), adaptationSet.getMaxBandwidth(), representation.getBandwidth())) {
                return new Violation("R3.4", "The value of the bandwidth attribute shall be in the range defined by the AdaptationSet.");
            }
        }
        return Violation.empty();
    }

    @ValidationRule("if (descendant::dash:Representation/@width < @minWidth or " +
            "descendant::dash:Representation/@width > @maxWidth) then false() else true()")
    private static Violation ruleR35(AdaptationSet adaptationSet) {
        for (Representation representation : adaptationSet.getRepresentations()) {
            if (!ValidationSupport.rangeCheck(adaptationSet.getMinWidth(), adaptationSet.getMaxWidth(), representation.getWidth())) {
                return new Violation("R3.5", "The value of the width attribute shall be in the range defined by the AdaptationSet.");
            }
        }
        return Violation.empty();
    }

    @ValidationRule("if (descendant::dash:Representation/@height < @minHeight or " +
            "descendant::dash:Representation/@height > @maxHeight) then false() else true()")
    private static Violation ruleR36(AdaptationSet adaptationSet) {
        for (Representation representation : adaptationSet.getRepresentations()) {
            if (!ValidationSupport.rangeCheck(adaptationSet.getMinHeight(), adaptationSet.getMaxHeight(), representation.getHeight())) {
                return new Violation("R3.6", "The value of the height attribute shall be in the range defined by the AdaptationSet.");
            }
        }
        return Violation.empty();
    }

    @ValidationRule("if (count(child::dash:Representation)=0) then false() else true()")
    private static Violation ruleR37(AdaptationSet adaptationSet) {
        if (adaptationSet.getRepresentations().isEmpty()) {
            return new Violation("R3.7", "An AdaptationSet shall have at least one Representation element.");
        }
        return Violation.empty();
    }

    @ValidationRule("if (" +
            "(child::dash:SegmentBase and child::dash:SegmentTemplate and child::dash:SegmentList) or " +
            "(child::dash:SegmentBase and child::dash:SegmentTemplate) or " +
            "(child::dash:SegmentBase and child::dash:SegmentList) or " +
            "(child::dash:SegmentTemplate and child::dash:SegmentList)) " +
            "then false() else true()")
    private static Violation ruleR38(AdaptationSet adaptationSet) {
        if ((adaptationSet.getSegmentBase() == null ? 0 : 1) +
                (adaptationSet.getSegmentList() == null ? 0 : 1) +
                (adaptationSet.getSegmentTemplate() == null ? 0 : 1) > 1) {
            return new Violation("R3.8", "At most one of SegmentBase, SegmentTemplate and SegmentList shall be defined in AdaptationSet.");
        }
        return Violation.empty();
    }

    @ValidationRule("if (" +
            "(@minFrameRate and (descendant::dash:Representation/@frameRate < @minFrameRate)) or " +
            "(@maxFrameRate and (descendant::dash:Representation/@frameRate > @maxFrameRate))" +
            ") then false() else true()")
    private static Violation ruleR39(AdaptationSet adaptationSet) {
        double minFrameRate;
        if (adaptationSet.getMinFrameRate() != null) {
            minFrameRate = adaptationSet.getMinFrameRate().toDouble();
        } else {
            minFrameRate = Double.MIN_VALUE;
        }

        double maxFrameRate;
        if (adaptationSet.getMaxFrameRate() != null) {
            maxFrameRate = adaptationSet.getMaxFrameRate().toDouble();
        } else {
            maxFrameRate = Double.MAX_VALUE;
        }

        for (Representation representation : adaptationSet.getRepresentations()) {
            if (representation.getFrameRate() == null)
                continue;

            double frameRate = representation.getFrameRate().toDouble();

            if (frameRate < minFrameRate || frameRate > maxFrameRate) {
                return new Violation("R3.9", "ISO/IEC 23009-1 Section 5.3.3.2: The value of the frameRate attribute shall be in the range defined by the AdaptationSet.");
            }
        }
        return Violation.empty();
    }

    @ValidationRule("if (contains(ancestor::dash:MPD/@profiles, 'http://dashif.org/guidelines/dash') " +
            "and @contentType='video' and not(@par)) then false() else true()")
    private static Violation ruleRD30(AdaptationSet adaptationSet, MPD mpd) {
        if (ValidationSupport.hasDashIOP(mpd) && "video".equals(adaptationSet.getContentType()) && adaptationSet.getPar() == null) {
            return new Violation("RD3.0", "DASH-IF IOP Section 3.2.4: \"For any Adaptation Sets with value of the @contentType=\"video\" the following attributes shall be present: @par\" violated here");
        }
        return Violation.empty();
    }

    @ValidationRule("if (contains(ancestor::dash:MPD/@profiles, 'http://dashif.org/guidelines/dash') " +
            "and @contentType='video' and (@scanType and not(@scanType='progressive'))) then false() else true()")
    private static Violation ruleRD31(AdaptationSet adaptationSet, MPD mpd) {
        if (!ValidationSupport.hasDashIOP(mpd)) {
            return Violation.empty();
        }

        if ("video".equals(adaptationSet.getContentType()) &&
                adaptationSet.getScanType() != null &&
                adaptationSet.getScanType() != VideoScanType.PROGRESSIVE) {
            return new Violation("RD3.1", "DASH-IF IOP Section 3.2.4: \"For Adaptation Set or for any Representation within an Adaptation Set with value of the @contentType=\"video\" the attribute @scanType shall either not be present or shall be set to 'progressive' \", violated here");
        }

        return Violation.empty();
    }

    @ValidationRule("if (contains(ancestor::dash:MPD/@profiles, 'http://dashif.org/guidelines/dash') " +
            "and @contentType='audio' and not(@lang)) then false() else true()")
    private static Violation ruleRD32(AdaptationSet adaptationSet, MPD mpd) {
        if (ValidationSupport.hasDashIOP(mpd) && "audio".equals(adaptationSet.getContentType()) && adaptationSet.getLang() == null) {
            return new Violation("RD3.2", "DASH-IF IOP Section 3.2.4: \"For any Adaptation Sets with value of the @contentType=\"audio\" the following attributes shall be present: @lang\" violated here");
        }
        return Violation.empty();
    }

    @ValidationRule("if (contains(ancestor::dash:MPD/@profiles, 'http://dashif.org/guidelines/dash') and " +
            "@contentType='video' and not(@maxWidth) and not(@width)) then false() else true()")
    private static Violation ruleRD33(AdaptationSet adaptationSet, MPD mpd) {
        if (ValidationSupport.hasDashIOP(mpd) && "video".equals(adaptationSet.getContentType()) && adaptationSet.getMaxWidth() == null && adaptationSet.getWidth() == null) {
            return new Violation("RD3.3", "DASH-IF IOP Section 3.2.4: \"For any Adaptation Sets with @contentType=\"video\" the following attributes shall be present: @maxWidth or @width\" violated here");
        }
        return Violation.empty();
    }

    @ValidationRule("if (contains(ancestor::dash:MPD/@profiles, 'http://dashif.org/guidelines/dash') and " +
            "@contentType='video' and not(@maxHeight) and not(@height)) then false() else true()")
    private static Violation ruleRD34(AdaptationSet adaptationSet, MPD mpd) {
        if (ValidationSupport.hasDashIOP(mpd) && "video".equals(adaptationSet.getContentType()) && adaptationSet.getMaxHeight() == null && adaptationSet.getHeight() == null) {
            return new Violation("RD3.4", "DASH-IF IOP Section 3.2.4: \"For any Adaptation Sets with @contentType=\"video\" the following attributes shall be present: @maxHeight or @height\" violated here");
        }
        return Violation.empty();
    }

    @ValidationRule("if (contains(ancestor::dash:MPD/@profiles, 'http://dashif.org/guidelines/dash') and " +
            "@contentType='video' and not(@maxFrameRate) and not(@frameRate)) then false() else true()")
    private static Violation ruleRD35(AdaptationSet adaptationSet, MPD mpd) {
        if (ValidationSupport.hasDashIOP(mpd) && "video".equals(adaptationSet.getContentType()) && adaptationSet.getMaxFrameRate() == null && adaptationSet.getFrameRate() == null) {
            return new Violation("RD3.5", "DASH-IF IOP Section 3.2.4: \"For any Adaptation Sets with @contentType=\"video\" the following attributes shall be present: @maxFrameRate or @frameRate\" violated here");
        }
        return Violation.empty();
    }

    @ValidationRule("if (contains(ancestor::dash:MPD/@profiles, 'http://dashif.org/guidelines/dash') and " +
            "contains(ancestor::dash:MPD/@profiles, 'urn:mpeg:dash:profile:isoff-live:2011' and " +
            "(not(@segmentAlignment) or @segmentAlignment='false'))) then false() else true()")
    private static Violation ruleRD36(AdaptationSet adaptationSet, MPD mpd) {
        if (ValidationSupport.hasDashIOP(mpd) && mpd.getProfiles().contains(Profile.MPEG_DASH_LIVE) &&
                (adaptationSet.getSegmentAlignment() == null || "false".equals(adaptationSet.getSegmentAlignment()))) {
            return new Violation("RD3.6", "DASH-IF IOP Section 3.2.2.2: For Live Profile @segmentAlignment shall be set to true for all Adaptation Sets");
        }
        return Violation.empty();
    }

    @ValidationRule("if (contains(ancestor::dash:MPD/@profiles, 'http://dashif.org/guidelines/dash') and " +
            "contains(ancestor::dash:MPD/@profiles, 'urn:mpeg:dash:profile:isoff-ondemand:2011' and " +
            "(not(@subSegmentAlignment) or @subSegmentAlignment='false'))) then false() else true()")
    private static Violation ruleRD37(AdaptationSet adaptationSet, MPD mpd) {
        if (ValidationSupport.hasDashIOP(mpd) && mpd.getProfiles().contains(Profile.MPEG_DASH_ON_DEMAND) &&
                (adaptationSet.getSubsegmentAlignment() == null || "false".equals(adaptationSet.getSubsegmentAlignment()))) {
            return new Violation("RD3.7", "DASH-IF IOP Section 3.2.2.2: For On-Demand Profile @subSegmentAlignment shall be set to true for all Adaptation Sets");
        }
        return Violation.empty();
    }

    private static final List<String> VALID_MIME_TYPES = Arrays.asList(
            "video/mp4", "audio/mp4", "application/mp4", "application/ttml+xml", "text/vtt");

    @ValidationRule("if (@mimeType and not((@mimeType = 'video/mp4') or (@mimeType = 'audio/mp4') or " +
            "(@mimeType = 'application/mp4') or (@mimeType = 'application/ttml+xml') or (@mimeType = 'text/vtt')))" +
            " then false() else true()")
    private static Violation ruleRD38(AdaptationSet adaptationSet) {
        String mimeType = adaptationSet.getMimeType();
        if (mimeType != null && !VALID_MIME_TYPES.contains(mimeType)) {
            return new Violation("RD3.8", "If a DASH-IF profile identifier is present, for the Adaptation Sets the mimeType shall be one of the five following type: \"video/mp4\", \"audio/mp4\", \"application/mp4\", \"application/ttml+xml or \"text/vtt\"");
        }
        return Violation.empty();
    }

    @ValidationRule("if (@id = preceding-sibling::dash:ContentComponent/@id) then false() else true()")
    private static Violation ruleR40(AdaptationSet adaptationSet) {
        HashSet<Long> ids = new HashSet<>();
        adaptationSet.getContentComponents().forEach(contentComponent -> ids.add(contentComponent.getId()));
        if (ids.size() != adaptationSet.getContentComponents().size()) {
            return new Violation("R4.0", "The id of each ContentComponent within an AdaptationSet shall be unique.");
        }
        return Violation.empty();
    }

    public static List<Violation> validate(MPD mpd, Period period, AdaptationSet adaptationSet) {
        List<Violation> violations = new ArrayList<>();
        violations.addAll(Arrays.asList(
            ruleR30(period),
            ruleRx38(period),
            ruleR31(adaptationSet),
            ruleR32(adaptationSet),
            ruleR33(adaptationSet),
            ruleR34(adaptationSet),
            ruleR35(adaptationSet),
            ruleR36(adaptationSet),
            ruleR37(adaptationSet),
            ruleR38(adaptationSet),
            ruleR39(adaptationSet),
            ruleRD30(adaptationSet, mpd),
            ruleRD31(adaptationSet, mpd),
            ruleRD32(adaptationSet, mpd),
            ruleRD33(adaptationSet, mpd),
            ruleRD34(adaptationSet, mpd),
            ruleRD35(adaptationSet, mpd),
            ruleRD36(adaptationSet, mpd),
            ruleRD37(adaptationSet, mpd),
            ruleRD38(adaptationSet),
            ruleR40(adaptationSet)));

        if (adaptationSet.getSegmentTemplate() != null) {
            violations.addAll(SegmentTemplateValidator.validate(mpd, adaptationSet.getSegmentTemplate()));
        }

        if (adaptationSet.getSegmentBase() != null) {
            violations.addAll(SegmentBaseValidator.validate(mpd, adaptationSet.getSegmentBase()));
        }

        if (adaptationSet.getSegmentList() != null) {
            violations.addAll(SegmentListValidator.validate(mpd, adaptationSet.getSegmentList()));
        }

        for (Descriptor framePacking : adaptationSet.getFramePackings()) {
            violations.addAll(FramePackingValidator.validate(adaptationSet, null, framePacking));
        }

        for (Descriptor role : adaptationSet.getRoles()) {
            violations.addAll(RoleValidator.validate(role));
        }

        for (Representation representation : adaptationSet.getRepresentations()) {
            violations.addAll(RepresentationValidator.validate(mpd, period, adaptationSet, representation));
        }

        for (Descriptor contentProtection : adaptationSet.getContentProtections()) {
            violations.addAll(ContentProtectionValidator.validate(adaptationSet, contentProtection));
        }

        for (Descriptor audioChannelConfiguration : adaptationSet.getAudioChannelConfigurations()) {
            violations.addAll(AudioChannelConfigurationValidator.validate(mpd, audioChannelConfiguration));
        }

        for (Descriptor supplementalProperty : adaptationSet.getSupplementalProperties()) {
            violations.addAll(SupplementalPropertyValidator.validate(adaptationSet, supplementalProperty));
        }

        return violations;
    }
}

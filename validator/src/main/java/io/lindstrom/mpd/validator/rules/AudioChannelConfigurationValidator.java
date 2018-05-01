package io.lindstrom.mpd.validator.rules;

import io.lindstrom.mpd.data.descriptor.Descriptor;
import io.lindstrom.mpd.data.MPD;

import java.util.Arrays;
import java.util.List;

public class AudioChannelConfigurationValidator {
    /*
     *
     * R15.*: Check the conformance of AudioChannelConfiguration
     *
     */

    @ValidationRule("if ((@schemeIdUri = 'urn:mpeg:dash:outputChannelPositionList:2012') and " +
            "not(count(tokenize(@value, ' ')) > 1)) then false() else true()")
    private static Violation ruleR150(Descriptor audioChannelConfiguration) {
        if ("urn:mpeg:dash:outputChannelPositionList:2012".equals(audioChannelConfiguration.getSchemeIdUri()) &&
                !(audioChannelConfiguration.getValue().split(" ").length > 1)) {
            return new Violation("R15.0", "If URI urn:mpeg:dash:outputChannelPositionList:2012 is " +
                    "used the value attribute shall be a space-delimited list as defined in ISO/IEC 23001-8.");
        }
        return Violation.empty();
    }

    @ValidationRule("if (contains(ancestor::dash:MPD/@profiles, 'http://dashif.org/guidelines/dashif#ac-4') and " +
            "not(@schemeIdUri='tag:dolby.com,2014:dash:audio_channel_configuration:2011')) then false() else true()")
    private static Violation ruleR151(MPD mpd, Descriptor audioChannelConfiguration) {
        if (mpd.getInteroperabilityPointsAndExtensions().contains("http://dashif.org/guidelines/dashif#ac-4") &&
                !"tag:dolby.com,2014:dash:audio_channel_configuration:2011".equals(audioChannelConfiguration.getSchemeIdUri())) {
            return new Violation("R15.1", "If profile http://dashif.org/guidelines/dashif#ac-4 is used, " +
                    "then schemeIdUri attribute shall be tag:dolby.com,2014:dash:audio_channel_configuration:2011.");
        }
        return Violation.empty();
    }

    @ValidationRule("if (contains(ancestor::dash:MPD/@profiles, 'http://dashif.org/guidelines/dashif#mha1') and " +
            "not(@schemeIdUri='urn:mpeg:mpegB:cicp:ChannelConfiguration')) then false() else true()")
    private static Violation ruleR152(MPD mpd, Descriptor audioChannelConfiguration) {
        if (mpd.getInteroperabilityPointsAndExtensions().contains("http://dashif.org/guidelines/dashif#mha1") &&
                !"urn:mpeg:mpegB:cicp:ChannelConfiguration".equals(audioChannelConfiguration.getSchemeIdUri())) {
            return new Violation("R15.2", "If profile http://dashif.org/guidelines/dashif#mha1 is used, " +
                    "then schemeIdUri attribute shall be urn:mpeg:mpegB:cicp:ChannelConfiguration.");
        }
        return Violation.empty();
    }

    public static List<Violation> validate(MPD mpd, Descriptor audioChannelConfiguration) {
        return Arrays.asList(
                ruleR150(audioChannelConfiguration),
                ruleR151(mpd, audioChannelConfiguration),
                ruleR152(mpd, audioChannelConfiguration));
    }
}

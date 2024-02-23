package io.lindstrom.mpd.transform;

import io.lindstrom.mpd.v2.AdaptationSet;
import io.lindstrom.mpd.v2.MPD;
import io.lindstrom.mpd.v2.Period;
import io.lindstrom.mpd.v2.Representation;

public record RepresentationContext(
        MPD mpd,
        Period period,
        AdaptationSet adaptationSet,
        Representation representation
) {
}
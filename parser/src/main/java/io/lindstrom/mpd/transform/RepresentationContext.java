package io.lindstrom.mpd.transform;

import io.lindstrom.mpd.data.AdaptationSet;
import io.lindstrom.mpd.data.MPD;
import io.lindstrom.mpd.data.Period;
import io.lindstrom.mpd.data.Representation;

public record RepresentationContext(
        MPD mpd,
        Period period,
        AdaptationSet adaptationSet,
        Representation representation
) {
}
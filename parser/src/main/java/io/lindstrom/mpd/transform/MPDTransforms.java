package io.lindstrom.mpd.transform;

import io.lindstrom.mpd.v2.AdaptationSet;
import io.lindstrom.mpd.v2.MPD;
import io.lindstrom.mpd.v2.Period;
import io.lindstrom.mpd.v2.Representation;

import java.util.Objects;
import java.util.function.Function;

public class MPDTransforms {
    private MPDTransforms() {
    }

    public MPD updateRepresentations(MPD mpd, Function<RepresentationContext, Representation> representationMapper) {
        return MPD.builder()
                .from(mpd)
                .periods(mpd.periods().stream()
                        .map(period -> Period.builder()
                                .from(period)
                                .adaptationSets(period.adaptationSets().stream()
                                        .map(adaptationSet -> AdaptationSet.builder()
                                                .from(adaptationSet)
                                                .representations(adaptationSet.representations().stream()
                                                        .map(representation -> representationMapper.apply(new RepresentationContext(mpd, period, adaptationSet, representation)))
                                                        .filter(Objects::nonNull)
                                                        .toList())
                                                .build())
                                        .toList())
                                .build())
                        .toList())
                .build();
    }
}
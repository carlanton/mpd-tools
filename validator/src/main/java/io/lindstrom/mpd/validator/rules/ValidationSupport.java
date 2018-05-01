package io.lindstrom.mpd.validator.rules;

import io.lindstrom.mpd.data.MPD;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class ValidationSupport {
    static boolean hasDashIOP(MPD mpd) {
        return mpd.getInteroperabilityPointsAndExtensions().stream()
                .anyMatch(string -> string.contains("http://dashif.org/guidelines/dash"));
    }

    static boolean atMostOneIsNonNull(Object ...args) {
        int j = 0;

        for (Object arg : args) {
            if (arg != null) {
                j++;
            }

            if (j > 1) {
                return false;
            }
        }

        return true;
    }

    static boolean uniqueIds(List<Object> ids) {
        HashSet<Object> seen = new HashSet<>();
        return ids.stream()
                .filter(Objects::nonNull)
                .allMatch(seen::add);
    }

    static boolean rangeCheck(Long minimum, Long maximum, Long value) {
        if (value == null) {
            return true;
        }

        long min = minimum == null ? Long.MIN_VALUE : minimum;
        long max = maximum == null ? Long.MAX_VALUE : maximum;

        return value >= min && value <= max;
    }
}

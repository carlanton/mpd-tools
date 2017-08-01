package io.lindstrom.mpd.support;

import java.util.Collections;
import java.util.List;

public class Utils {
    private static Class<? extends List> UNMODIFIABLE_LIST_CLASS =
            Collections.unmodifiableList(Collections.emptyList()).getClass();

    public static <T> List<T> unmodifiableList(List<T> list) {
        return UNMODIFIABLE_LIST_CLASS.isInstance(list) ? list : Collections.unmodifiableList(list);
    }
}

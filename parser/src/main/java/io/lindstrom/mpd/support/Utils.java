package io.lindstrom.mpd.support;

import java.util.Collections;
import java.util.List;

public class Utils {
    private static final Class<? extends List> UNMODIFIABLE_LIST_CLASS =
            Collections.unmodifiableList(Collections.emptyList()).getClass();

    public static <T> List<T> unmodifiableList(List<T> list) {
        if (list == null) {
            return Collections.emptyList();
        } else if (UNMODIFIABLE_LIST_CLASS.isInstance(list)) {
            return list;
        } else {
            return Collections.unmodifiableList(list);
        }
    }
}

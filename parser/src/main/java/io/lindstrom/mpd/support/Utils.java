package io.lindstrom.mpd.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utils {
    private static final Class<?> UNMODIFIABLE_LIST_CLASS =
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

    @SafeVarargs
    public static <T> List<T> varargsToList(T head, T ...tail) {
        if (tail.length == 0) {
            return Collections.singletonList(head);
        } else {
            List<T> list = new ArrayList<>();
            list.add(head);
            for (T element : tail) {
                list.add(element);
            }
            return list;
        }
    }
}

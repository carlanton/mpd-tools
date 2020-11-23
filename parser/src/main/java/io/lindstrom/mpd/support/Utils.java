package io.lindstrom.mpd.support;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static <T> List<T> unmodifiableList(List<T> list) {
        if (list == null) {
            return List.of();
        } else {
            return List.copyOf(list);
        }
    }

    @SafeVarargs
    public static <T> List<T> varargsToList(T head, T ...tail) {
        if (tail.length == 0) {
            return List.of(head);
        } else {
            List<T> list = new ArrayList<>();
            list.add(head);
            list.addAll(List.of(tail));
            return List.copyOf(list);
        }
    }
}

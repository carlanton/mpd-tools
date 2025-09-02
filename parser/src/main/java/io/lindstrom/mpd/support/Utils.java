package io.lindstrom.mpd.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Utils {
    public static <T> List<T> unmodifiableList(List<T> list) {
        if (list == null) {
            return List.of();
        } else {
            return List.copyOf(list);
        }
    }

    public static <K, V> Map<K, V> unmodifiableMap(Map<K, V> map) {
        if (map == null) {
            return Map.of();
        } else {
            return Map.copyOf(map);
        }
    }

    @SafeVarargs
    @SuppressWarnings("varargs")
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
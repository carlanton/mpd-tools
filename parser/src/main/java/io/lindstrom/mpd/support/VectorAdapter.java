package io.lindstrom.mpd.support;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class VectorAdapter<T> extends XmlAdapter<String, List<T>> {
    private static final String DELIMITER = " ";

    @Override
    public List<T> unmarshal(String v) throws Exception {
        return Arrays.stream(v.split(DELIMITER))
                .map(this::toType)
                .collect(Collectors.toList());
    }

    @Override
    public String marshal(List<T> v) throws Exception {
        return v.stream()
                .map(this::toString)
                .collect(Collectors.joining(DELIMITER));
    }

    protected abstract T toType(String string);

    protected abstract String toString(T value);

    public static class LongAdapter extends VectorAdapter<Long> {
        @Override
        protected Long toType(String string) {
            return Long.parseLong(string);
        }

        @Override
        protected String toString(Long value) {
            return value.toString();
        }
    }

    public static class StringAdapter extends VectorAdapter<String> {
        @Override
        protected String toType(String string) {
            return string;
        }

        @Override
        protected String toString(String value) {
            return value;
        }
    }
}

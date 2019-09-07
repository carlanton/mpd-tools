package io.lindstrom.mpd.support;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class UtilsTest {

    @Test
    public void immutableList() throws Exception {
        assertThrows(UnsupportedOperationException.class, () -> {
            List<String> xs = Utils.unmodifiableList(new ArrayList<>());
            xs.add("element");
        });
    }

    @Test
    public void doNotWrap() throws Exception {
        List<String> list = Utils.unmodifiableList(new ArrayList<>());

        assertTrue(list == Utils.unmodifiableList(list));
    }

    @Test
    public void nonNull() throws Exception {
        assertNotNull(Utils.unmodifiableList(null));
    }

    @Test
    public void varargsToList() throws Exception {
        assertEquals(Arrays.asList(1, 2, 3, 4), Utils.varargsToList(1, 2, 3, 4));
    }

    @Test
    public void varargsToListSingleElement() throws Exception {
        assertEquals(Collections.singletonList("a"), Utils.varargsToList("a"));
    }
}
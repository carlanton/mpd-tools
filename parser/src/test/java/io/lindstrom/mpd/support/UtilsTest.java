package io.lindstrom.mpd.support;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class UtilsTest {

    @Test(expected = UnsupportedOperationException.class)
    public void immutableList() throws Exception {
        List<String> xs = Utils.unmodifiableList(new ArrayList<>());
        xs.add("element");
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
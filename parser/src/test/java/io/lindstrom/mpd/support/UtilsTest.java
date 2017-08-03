package io.lindstrom.mpd.support;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

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
}
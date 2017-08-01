package io.lindstrom.mpd.support;

import org.junit.Test;

import java.util.Arrays;

import static io.lindstrom.mpd.support.VectorAdapter.*;
import static org.junit.Assert.assertEquals;

public class VectorAdapterTest {
    @Test
    public void longAdapterUnmarshal() throws Exception {
        assertEquals(Arrays.asList(1L, 2L, 3L, 4L, 5L),
                new LongAdapter().unmarshal("1 2 3 4 5"));
    }

    @Test
    public void longAdapterMarshal() throws Exception {
        assertEquals("1 2 3 4 5",
                new LongAdapter().marshal(Arrays.asList(1L, 2L, 3L, 4L, 5L)));
    }

    @Test
    public void stringAdapterUnmarshal() throws Exception {
        assertEquals(Arrays.asList("a", "b", "c", "d"),
                new VectorAdapter.StringAdapter().unmarshal("a b c d"));
    }

    @Test
    public void stringAdapterMarshal() throws Exception {
        assertEquals("a b c d",
                new VectorAdapter.StringAdapter().marshal(Arrays.asList("a", "b", "c", "d")));
    }
}
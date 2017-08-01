package io.lindstrom.mpd.support;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UtilsTest {

    @Test
    public void name() throws Exception {
        List<String> xs = Utils.unmodifiableList(new ArrayList<>());
        System.out.println(Utils.unmodifiableList(xs).getClass());
    }
}
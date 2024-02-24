package io.lindstrom.mpd.support;

import com.fasterxml.jackson.databind.JsonMappingException;
import io.lindstrom.mpd.data.FrameRate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class FrameRateSerializerTest extends ObjectMapperTestBase<FrameRate> {

    @BeforeEach
    public void FrameRateSerializerTest() {
        initObjectMapperTestBase(FrameRate.class);
    }

    @Test
    public void serialize1() throws Exception {
        assertEquals("1/2", write(FrameRate.of(1, 2L)));
    }

    @Test
    public void serialize2() throws Exception {
        assertEquals("25", write(FrameRate.of(25)));
    }

    @Test
    public void deserialize1() throws Exception {
        assertEquals(FrameRate.of(1, 2L), read("1/2"));
    }

    @Test
    public void deserialize2() throws Exception {
        assertEquals(FrameRate.of(25), read("25"));
    }

    @Test
    public void deserializeFail() throws Exception {
        assertThrows(JsonMappingException.class, () -> {
            read("25t");
        });
    }
}
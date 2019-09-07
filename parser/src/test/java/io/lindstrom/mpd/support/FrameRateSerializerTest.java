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
        initObjectMapperTestBase(FrameRate.class, new FrameRateSerializer(), new FrameRateDeserializer());
    }

    @Test
    public void serialize1() throws Exception {
        assertEquals("1/2", write(new FrameRate(1, 2L)));
    }

    @Test
    public void serialize2() throws Exception {
        assertEquals("25", write(new FrameRate(25, null)));
    }

    @Test
    public void deserialize1() throws Exception {
        assertEquals(new FrameRate(1, 2L), read("1/2"));
    }

    @Test
    public void deserialize2() throws Exception {
        assertEquals(new FrameRate(25, null), read("25"));
    }

    @Test
    public void deserializeFail() throws Exception {
        assertThrows(JsonMappingException.class, () -> {
            read("25t");
        });
    }
}
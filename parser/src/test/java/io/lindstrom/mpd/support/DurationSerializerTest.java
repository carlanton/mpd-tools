package io.lindstrom.mpd.support;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class DurationSerializerTest extends ObjectMapperTestBase<Duration> {
    public DurationSerializerTest() {
        super(Duration.class, new DurationSerializer(), new DurationDeserializer());
    }

    @Test
    public void testXmlDurationFallback() throws Exception {
        assertEquals(Duration.parse("PT876600H"), read("P100Y"));
    }

    @Test
    public void simpleDuration() throws Exception {
        assertEquals(Duration.ofHours(2), read("PT2H"));
    }

    @Test
    public void serialize1() throws Exception {
        assertEquals("PT10S", write(Duration.ofSeconds(10)));
    }

    @Test(expected = JsonMappingException.class)
    public void deserializeFail() throws Exception {
        read("x");
    }
}
package io.lindstrom.mpd.support;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.junit.Test;

import java.time.OffsetDateTime;

import static org.junit.Assert.assertEquals;

public class OffsetDateTimeSerializerTest extends ObjectMapperTestBase<OffsetDateTime> {
    public OffsetDateTimeSerializerTest() {
        super(OffsetDateTime.class, new OffsetDateTimeSerializer(), new OffsetDateTimeDeserializer());
    }

    @Test
    public void serialize1() throws Exception {
        assertEquals("2016-07-08T09:08:10Z", write(OffsetDateTime.parse("2016-07-08T09:08:10Z")));
    }

    @Test
    public void deserialize1() throws Exception {
        assertEquals(OffsetDateTime.parse("2016-07-08T09:08:10Z"), read("2016-07-08T09:08:10Z"));
    }

    @Test
    public void deserializeWithoutTimezone() throws Exception {
        assertEquals(OffsetDateTime.parse("2016-07-08T09:08:10Z"), read("2016-07-08T09:08:10"));
    }

    @Test(expected = JsonMappingException.class)
    public void deserializeFail() throws Exception {
        read("2016-07-08T09");
    }
}
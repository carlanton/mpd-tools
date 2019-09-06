package io.lindstrom.mpd.support;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OffsetDateTimeSerializerTest extends ObjectMapperTestBase<OffsetDateTime> {

    @BeforeEach
    public void OffsetDateTimeSerializerTest() {
        initObjectMapperTestBase(OffsetDateTime.class, new OffsetDateTimeSerializer(), new OffsetDateTimeDeserializer());
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

    @Test
    public void deserializeFail() throws Exception {
        assertThrows(JsonMappingException.class, () -> {
            read("2016-07-08T09");
        });
    }
}
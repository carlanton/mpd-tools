package io.lindstrom.mpd.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;

public abstract class ObjectMapperTestBase<T> {
    protected ObjectMapper objectMapper;
    protected Class<T> clazz;

    protected void initObjectMapperTestBase(Class<T> clazz, JsonSerializer<T> serializer, JsonDeserializer<T> deserializer) {
        this.objectMapper = new ObjectMapper()
                .registerModule(new SimpleModule() {
                    @Override
                    public void setupModule(SetupContext context) {
                        addDeserializer(clazz, deserializer);
                        addSerializer(clazz, serializer);
                        super.setupModule(context);
                    }
                });
        this.clazz = clazz;
    }

    T read(String string) throws IOException {
        return objectMapper.readValue('"' + string + '"', clazz);
    }

    String write(T object) throws JsonProcessingException {
        String string = objectMapper.writeValueAsString(object);
        return string.substring(1, string.length() - 1);
    }
}

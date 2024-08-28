package io.lindstrom.mpd;

import com.ctc.wstx.api.WriterConfig;
import com.ctc.wstx.stax.WstxInputFactory;
import com.ctc.wstx.stax.WstxOutputFactory;
import com.ctc.wstx.sw.XmlWriter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.lindstrom.mpd.data.MPD;
import io.lindstrom.mpd.support.*;
import org.codehaus.stax2.XMLStreamWriter2;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.OffsetDateTime;

public class MPDParser {

    private final ObjectMapper objectMapper;

    public MPDParser() {
        this(defaultObjectMapper());
    }

    public MPDParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public MPD parse(InputStream inputStream) throws IOException {
        return objectMapper.readValue(inputStream, MPD.class);
    }

    public MPD parse(String content) throws IOException {
        return objectMapper.readValue(content, MPD.class);
    }

    public String writeAsString(MPD mpd) throws JsonProcessingException {
        return objectMapper.writeValueAsString(mpd);
    }

    public byte[] writeAsBytes(MPD mpd) throws JsonProcessingException {
        return objectMapper.writeValueAsBytes(mpd);
    }

    public static ObjectMapper defaultObjectMapper() {
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        module.addSerializer(OffsetDateTime.class, new OffsetDateTimeSerializer())
                .addDeserializer(OffsetDateTime.class, new OffsetDateTimeDeserializer())
                .addSerializer(Duration.class, new DurationSerializer())
                .addDeserializer(Duration.class, new DurationDeserializer());

        return new XmlMapper(new XmlFactory(new WstxInputFactory(), new WstxPrefixedOutputFactory()), module)
                .enable(SerializationFeature.INDENT_OUTPUT)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                .setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
    }

    private static class WstxPrefixedOutputFactory extends WstxOutputFactory {
        @Override
        protected XMLStreamWriter2 createSW(String enc, WriterConfig cfg, XmlWriter xw) {
            XMLStreamWriter2 streamWriter = super.createSW(enc, cfg, xw);
            try {
                streamWriter.setPrefix("xsi", "http://www.w3.org/2001/XMLSchema-instance");
                streamWriter.setPrefix("xlink", "http://www.w3.org/1999/xlink");
                streamWriter.setPrefix("cenc", "urn:mpeg:cenc:2013");
                streamWriter.setPrefix("mspr", "urn:microsoft:playready");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return streamWriter;
        }
    }
}

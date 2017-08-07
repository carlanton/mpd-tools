package io.lindstrom.mpd;

import com.ctc.wstx.api.WriterConfig;
import com.ctc.wstx.stax.WstxInputFactory;
import com.ctc.wstx.stax.WstxOutputFactory;
import com.ctc.wstx.sw.XmlWriter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import io.lindstrom.mpd.data.MPD;
import io.lindstrom.mpd.support.ParserModule;
import org.codehaus.stax2.XMLStreamWriter2;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStream;

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
        return new XmlMapper(new XmlFactory(new WstxInputFactory(), new WstxPrefixedOutputFactory()))
                .enable(SerializationFeature.INDENT_OUTPUT)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)
                .configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE, true)
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                .registerModule(new ParserModule())
                .setAnnotationIntrospector(AnnotationIntrospector.pair(
                        new JaxbAnnotationIntrospector(TypeFactory.defaultInstance()),
                        new JacksonAnnotationIntrospector()));
    }

    private static class WstxPrefixedOutputFactory extends WstxOutputFactory {
        @Override
        protected XMLStreamWriter2 createSW(String enc, WriterConfig cfg, XmlWriter xw) {
            XMLStreamWriter2 streamWriter = super.createSW(enc, cfg, xw);
            try {
                streamWriter.setPrefix("xsi", "http://www.w3.org/2001/XMLSchema-instance");
                streamWriter.setPrefix("xlink", "http://www.w3.org/1999/xlink");
            } catch (XMLStreamException e) {
                throw new RuntimeException(e);
            }
            return streamWriter;
        }
    }
}

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

import javax.xml.stream.XMLInputFactory;
import java.io.*;
import java.time.Duration;
import java.time.OffsetDateTime;

public class MPDParser {
    // Namespace URIs - define them as constants
    public static final String XSI_NAMESPACE_URI = "http://www.w3.org/2001/XMLSchema-instance";
    public static final String XLINK_NAMESPACE_URI = "http://www.w3.org/1999/xlink";
    public static final String CENC_NAMESPACE_URI = "urn:mpeg:cenc:2013";
    public static final String MSPR_NAMESPACE_URI = "urn:microsoft:playready";
    public static final String DASHIF_NAMESPACE_URI = "https://dashif.org/guidelines/clearKey";
    public static final String SCTE35_NAMESPACE_URI = "urn:scte:scte35:2013:xml";
    public static final String SCTE35_BINARY_NAMESPACE_URI = "urn:scte:scte35:2014:xml+bin";

    private final ObjectMapper objectMapper;

    public MPDParser() {
        this(defaultObjectMapper());
    }

    public MPDParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    // Reverted parse method
    public MPD parse(InputStream inputStream) throws IOException {
        return objectMapper.readValue(inputStream, MPD.class);
    }

    // Reverted parse method
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

        // Standard namespace-aware input factory configuration
        WstxInputFactory inputFactory = new WstxInputFactory();
        inputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);
        inputFactory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
        inputFactory.setProperty(XMLInputFactory.SUPPORT_DTD, false);

        // Use the prefixed output factory for serialization
        WstxPrefixedOutputFactory outputFactory = new WstxPrefixedOutputFactory();

        return new XmlMapper(new XmlFactory(inputFactory, outputFactory), module)
                .enable(SerializationFeature.INDENT_OUTPUT)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)
                .configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE, true)
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                .setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
    }

    /**
     * Output factory that adds required namespace prefixes during serialization.
     */
    private static class WstxPrefixedOutputFactory extends WstxOutputFactory {
        @Override
        protected XMLStreamWriter2 createSW(String enc, WriterConfig cfg, XmlWriter xw) {
            XMLStreamWriter2 streamWriter = super.createSW(enc, cfg, xw);
            try {
                // Set prefixes for known namespaces
                streamWriter.setPrefix("xsi", XSI_NAMESPACE_URI);
                streamWriter.setPrefix("xlink", XLINK_NAMESPACE_URI);
                streamWriter.setPrefix("cenc", CENC_NAMESPACE_URI);
                streamWriter.setPrefix("mspr", MSPR_NAMESPACE_URI);
                streamWriter.setPrefix("dashif", DASHIF_NAMESPACE_URI);
                streamWriter.setPrefix("scte35", SCTE35_NAMESPACE_URI);
                streamWriter.setPrefix("scte", SCTE35_BINARY_NAMESPACE_URI);
            } catch (Exception e) {
                throw new RuntimeException("Failed to set namespace prefixes on XMLStreamWriter", e);
            }
            return streamWriter;
        }
    }
}
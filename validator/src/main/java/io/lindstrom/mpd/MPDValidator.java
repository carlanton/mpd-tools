package io.lindstrom.mpd;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.IOException;
import java.io.StringReader;

public class MPDValidator {
    private final Schema schema;

    public MPDValidator() throws SAXException {
        schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                .newSchema(MPDValidator.class.getResource("/DASH-MPD.xsd"));
    }

    public void validate(String mpdString) throws IOException, SAXException {
        validate(new StreamSource(new StringReader(mpdString)));
    }

    public void validate(Source source) throws IOException, SAXException {
        schema.newValidator().validate(source);
    }
}

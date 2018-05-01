package io.lindstrom.mpd.validator;

import io.lindstrom.mpd.MPDParser;
import io.lindstrom.mpd.data.MPD;
import io.lindstrom.mpd.validator.rules.Violation;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public class MPDValidator {
    private final Schema schema;
    private final MPDParser mpdParser;

    public MPDValidator() throws SAXException {
        this(new MPDParser());
    }

    public MPDValidator(MPDParser mpdParser) throws SAXException {
        this.schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                .newSchema(MPDValidator.class.getResource("/DASH-MPD.xsd"));
        this.mpdParser = mpdParser;
    }

    public void validate(MPD mpd) throws IOException, SAXException, ManifestValidationException {
        xsdValidation(mpd);

        List<Violation> violations = io.lindstrom.mpd.validator.rules.MPDValidator.validate(mpd);
        if (!violations.isEmpty()) {
            throw new ManifestValidationException(String.format("Found %d validation errors", violations.size()), violations);
        }
    }

    void xsdValidation(MPD mpd) throws IOException, SAXException {
        byte[] buf = mpdParser.writeAsBytes(mpd);

        /*
        int i = 1;
        for (String line : mpdParser.writeAsString(mpd).split("\n")) {
            System.out.printf("%d: %s\n", i++, line);
        }
        */

        Source source = new StreamSource(new ByteArrayInputStream(buf));
        schema.newValidator().validate(source);
    }
}

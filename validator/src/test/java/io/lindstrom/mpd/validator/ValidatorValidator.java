package io.lindstrom.mpd.validator;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.lindstrom.mpd.MPDParser;
import io.lindstrom.mpd.support.DurationDeserializer;
import io.lindstrom.mpd.validator.rules.MPDValidator;
import io.lindstrom.mpd.validator.rules.Violation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidatorValidator {
    private static final MPDParser MPD_PARSER = new MPDParser(MPDParser.defaultObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerModule(new SimpleModule()
                    .addDeserializer(Duration.class, new ModifiedDurationDeserializer())));

    private static final List<String> IGNORED_SAMPLES = Arrays.asList("ex57.mpd", "exRD3_1.mpd");

    static List<List<Path>> params() throws IOException {
        try (Stream<Path> paths = Files.list(Paths.get("src/test/resources/schematron/examples/"))) {
            return paths.filter(p -> !IGNORED_SAMPLES.contains(p.getFileName().toString()))
                    .map(input -> {
                        String resultFileName = "result_" + input.getFileName().toString().replace(".mpd", ".xml");
                        Path output = input.getParent().resolveSibling("output/" + resultFileName);
                        return List.of(input, output);
                    }).collect(Collectors.toList());
        }
    }

    private Path input;
    private Path schematronOutput;

    void setValidatorValidator(List<Path> paths) {
        this.input = paths.get(0);
        this.schematronOutput = paths.get(1);
    }

    @ParameterizedTest
    @MethodSource("params")
    public void test01(List<Path> paths) throws Exception {
        setValidatorValidator(paths);
        assertEquals(
                schematronErrors(), validatorErrors(), "Validation results for " + input.getFileName() + " should be equal");
    }

    private List<String> validatorErrors() throws Exception {
        try (InputStream inputStream = Files.newInputStream(input)) {
            return MPDValidator.validate(MPD_PARSER.parse(inputStream)).stream()
                    .map(Violation::getMessage)
                    .sorted()
                    .collect(Collectors.toList());
        }
    }

    private List<String> schematronErrors() throws Exception {
        Document document = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(schematronOutput.toFile());

        NodeList nodeList = (NodeList) XPathFactory.newInstance()
                .newXPath()
                .compile("//*[local-name() = 'text']")
                .evaluate(document, XPathConstants.NODESET);

        return IntStream.range(0, nodeList.getLength())
                .mapToObj(nodeList::item)
                .map(Node::getTextContent)
                .map(String::trim)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    private static class ModifiedDurationDeserializer extends DurationDeserializer {
        @Override
        public Duration deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            try {
                return super.deserialize(p, ctxt);
            } catch (Exception e) {
                return Duration.parse("PT" + p.getText()); // hax
            }
        }
    }
}

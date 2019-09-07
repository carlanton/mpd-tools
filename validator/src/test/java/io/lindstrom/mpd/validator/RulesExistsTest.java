package io.lindstrom.mpd.validator;

import io.lindstrom.mpd.validator.rules.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class RulesExistsTest {
    private static final List<Class<?>> VALIDATORS = Arrays.asList(
            AdaptationSetValidator.class,
            AudioChannelConfigurationValidator.class,
            ContentProtectionValidator.class,
            EventStreamValidator.class,
            FramePackingValidator.class,
            io.lindstrom.mpd.validator.rules.MPDValidator.class,
            PeriodValidator.class,
            RepresentationValidator.class,
            RoleValidator.class,
            SegmentBaseValidator.class,
            SegmentListValidator.class,
            SegmentTemplateValidator.class,
            SegmentTimelineValidator.class,
            SubRepresentationValidator.class,
            SupplementalPropertyValidator.class);

    private static final Set<String> DEFINED_RULES = VALIDATORS.stream()
            .flatMap(clazz -> Arrays.stream(clazz.getDeclaredMethods()))
            .filter(method -> method.getAnnotation(ValidationRule.class) != null)
            .map(method -> method.getAnnotation(ValidationRule.class).value())
            .collect(Collectors.toSet());

    static List<String> rules() throws Exception {
        Document document = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(new File("src/test/resources/schematron/schematron.xsd"));

        NodeList asserts = (NodeList) XPathFactory.newInstance()
                .newXPath()
                .compile("//assert")
                .evaluate(document, XPathConstants.NODESET);

        return IntStream.range(0, asserts.getLength())
                .mapToObj(asserts::item)
                .map(Element.class::cast)
                .map(e -> e.getAttribute("test"))
                .collect(Collectors.toList());
    }

    @ParameterizedTest
    @MethodSource("rules")
    public void ruleIsDefined(String rule) throws Exception {
        assertTrue(DEFINED_RULES.contains(rule), "Rule is not defined: " + rule);
    }
}
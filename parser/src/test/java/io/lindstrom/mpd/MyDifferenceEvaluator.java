package io.lindstrom.mpd;

import org.w3c.dom.Attr;
import org.w3c.dom.Node;
import org.xmlunit.diff.Comparison;
import org.xmlunit.diff.ComparisonResult;
import org.xmlunit.diff.ComparisonType;
import org.xmlunit.diff.DifferenceEvaluator;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

public class MyDifferenceEvaluator implements DifferenceEvaluator {
    private final DateTimeFormatter formatter =
            new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd'T'HH:mm:ss[XXXXX]")
                    .parseDefaulting(ChronoField.OFFSET_SECONDS, 0)
                    .toFormatter();

    // Define the MPD namespace URI
    private static final String MPD_NAMESPACE_URI = "urn:mpeg:dash:schema:mpd:2011";

    // Standard namespaces added by the serializer that might be missing in simple inputs
    private static final Set<String> IGNORED_NS_PREFIXES_ON_ROOT = Set.of(
        "xsi", "xlink", "cenc", "mspr", "dashif", "scte", "scte35"
    );

    @Override
    public ComparisonResult evaluate(Comparison comparison, ComparisonResult outcome) {
        if (outcome == ComparisonResult.EQUAL) {
            return outcome;
        }

        final Node controlNode = comparison.getControlDetails().getTarget();
        final Node testNode = comparison.getTestDetails().getTarget();

        // --- Ignore Default MPD Namespace Declaration Difference --- 
        if (comparison.getType() == ComparisonType.NAMESPACE_URI && 
            controlNode != null && "MPD".equals(controlNode.getLocalName()) && controlNode.getParentNode() == null &&
            testNode != null && "MPD".equals(testNode.getLocalName()) && testNode.getParentNode() == null) {
                
            String controlUri = comparison.getControlDetails().getValue() != null ? comparison.getControlDetails().getValue().toString() : null;
            String testUri = comparison.getTestDetails().getValue() != null ? comparison.getTestDetails().getValue().toString() : null;

            // If the difference is that the test node has the MPD default namespace
            // and the control node doesn't (or vice-versa), ignore it.
            if ((MPD_NAMESPACE_URI.equals(testUri) && controlUri == null) || 
                (testUri == null && MPD_NAMESPACE_URI.equals(controlUri))) {
                System.out.println("INFO: Ignoring XMLUnit difference: Default MPD Namespace URI mismatch on root.");
                return ComparisonResult.EQUAL;
            }
        }

        // --- Ignore Extra/Missing Standard Namespace Prefix Declarations on Root --- 
        if (comparison.getType() == ComparisonType.ATTR_NAME_LOOKUP &&
            controlNode != null && "MPD".equals(controlNode.getLocalName()) && controlNode.getParentNode() == null &&
            testNode != null && "MPD".equals(testNode.getLocalName()) && testNode.getParentNode() == null) {

            String attrName = null;
            // Determine the attribute name based on which side has it (control=expected, test=actual)
            if (comparison.getControlDetails().getValue() != null) {
                attrName = comparison.getControlDetails().getValue().toString();
            } else if (comparison.getTestDetails().getValue() != null) {
                attrName = comparison.getTestDetails().getValue().toString();
            }

            // Check if the attribute difference involves an 'xmlns:' prefix
            if (attrName != null && attrName.startsWith("xmlns:")) {
                String prefix = attrName.substring(6); // Get the prefix part
                // Ignore if it's one of the standard prefixes added by our serializer
                if (IGNORED_NS_PREFIXES_ON_ROOT.contains(prefix)) {
                     System.out.println("INFO: Ignoring XMLUnit difference: Namespace declaration for prefix '" + prefix + "' on root.");
                     return ComparisonResult.EQUAL;
                }
            }
        }
        
        if ((controlNode instanceof Attr && testNode instanceof Attr)) {
            if (similarAttributes((Attr) controlNode, (Attr) testNode)) {
                return ComparisonResult.SIMILAR;
            } else {
                return outcome;
            }
        }

        return outcome;
    }


    private boolean similarAttributes(Attr control, Attr test) {
        // Sanity check
        if (!control.getName().equals(test.getName())) {
            return false;
        }

        final String controlValue = control.getValue();
        final String testValue = test.getValue();

        if (control.getName().equals("profiles")) {
            return sortAndTrimProfiles(controlValue).equals(sortAndTrimProfiles(testValue));
        }

        try {
            if (parseDuration(controlValue) == parseDuration(testValue)) {
                return true;
            }
        } catch (Exception e) {
            // ignore
        }

        try {
            if (Double.parseDouble(controlValue) == Double.parseDouble(testValue)) {
                return true;
            }
        } catch (Exception e) {
            // ignore
        }

        try {
            if (OffsetDateTime.parse(controlValue, formatter).equals(OffsetDateTime.parse(testValue, formatter))) {
                return true;
            }
        } catch (Exception e) {
            // ignore
        }

        return false;
    }


    private long parseDuration(String value) throws DatatypeConfigurationException {
        return DatatypeFactory.newInstance().newDuration(value).getTimeInMillis(new Date(0));
    }

    private String sortAndTrimProfiles(String profiles) {
        return Arrays.stream(profiles.split(","))
                .map(String::trim)
                .sorted()
                .collect(Collectors.joining(","));
    }
}

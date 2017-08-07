package io.lindstrom.mpd;

import org.w3c.dom.Attr;
import org.w3c.dom.Node;
import org.xmlunit.diff.Comparison;
import org.xmlunit.diff.ComparisonResult;
import org.xmlunit.diff.DifferenceEvaluator;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

public class MyDifferenceEvaluator implements DifferenceEvaluator {
    private final DateTimeFormatter formatter =
            new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd'T'HH:mm:ss[XXXXX]")
                    .parseDefaulting(ChronoField.OFFSET_SECONDS, 0)
                    .toFormatter();

    @Override
    public ComparisonResult evaluate(Comparison comparison, ComparisonResult outcome) {
        if (outcome == ComparisonResult.EQUAL) {
            return outcome; // only evaluate differences.
        }

        Node controlNode = comparison.getControlDetails().getTarget();
        Node testNode = comparison.getTestDetails().getTarget();

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

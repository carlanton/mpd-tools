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
import java.util.Date;

public class MyDifferenceEvalutar implements DifferenceEvaluator {
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


        if (controlNode instanceof Attr && testNode instanceof Attr) {
            Attr controlAttribute = (Attr) controlNode;
            Attr testAttribute = (Attr) testNode;

            try {
                long test = parseDuration(testAttribute.getValue());
                long control = parseDuration(controlAttribute.getValue());
                if (test == control) {
                    return ComparisonResult.SIMILAR;
                }
            } catch (Exception e) {
                // ignore
            }

            try {
                double test = Double.parseDouble(testAttribute.getValue());
                double control = Double.parseDouble(controlAttribute.getValue());
                if (test == control) {
                    return ComparisonResult.SIMILAR;
                }
            } catch (Exception e) {
                // ignore
            }

            try {
                OffsetDateTime test = OffsetDateTime.parse(testAttribute.getValue(), formatter);
                OffsetDateTime control = OffsetDateTime.parse(controlAttribute.getValue(), formatter);
                if (test.equals(control)) {
                    return ComparisonResult.SIMILAR;
                }
            } catch (Exception e) {
                // ignore
            }
        }

        return outcome;
    }

    private long parseDuration(String value) throws DatatypeConfigurationException {
        return DatatypeFactory.newInstance().newDuration(value).getTimeInMillis(new Date(0));
    }
}

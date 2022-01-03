package io.lindstrom.mpd.support;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DurationDeserializer extends JsonDeserializer<Duration> {
    private static final int HOURS_PER_YEAR = 8766;
    private static final int MINUTES_PER_MONTH = 43830;
    private static final int HOURS_PER_DAY = 24;

    // PnYnMnDTnHnMnS
    private static final Pattern PATTERN =
            Pattern.compile("([-+]?)P" +
                            "(?:([-+]?[0-9]+)Y)?" +
                            "(?:([-+]?[0-9]+)M)?" +
                            "(?:([-+]?[0-9]+)D)?" +
                            "(T(?:([-+]?[0-9]+)H)?(?:([-+]?[0-9]+)M)?(?:([-+]?[0-9]+)(?:[.,]([0-9]{0,12}))?S)?)?",
                    Pattern.CASE_INSENSITIVE);

    private static Duration estimateDuration(long years, long months, long days) {
        if (years > 0 || months > 0 || days > 0) {
            return Duration.ofHours(HOURS_PER_YEAR * years)
                    .plusMinutes(MINUTES_PER_MONTH * months)
                    .plusHours(HOURS_PER_DAY * days);
        } else {
            return Duration.ZERO;
        }
    }

    private static boolean isNegative(CharSequence text, int start, int end) {
        return (start >= 0 && end == start + 1 && text.charAt(start) == '-');
    }

    static long parseFraction(CharSequence text, int start, int end, boolean negate) {
        // regex limits to [0-9]{0,12}
        if (start < 0 || end < 0 || end - start == 0) {
            return 0;
        }

        // Duration only handle nano second resolution but xs:duration allows 12 decimals.
        if (end - start > 9) {
            end = start + 9;
        }

        long fraction = Long.parseLong(text.subSequence(start, end).toString());

        // for number strings smaller than 9 digits, interpret as if there were trailing zeros
        for (int i = end - start; i < 9; i++) {
            fraction *= 10;
        }

        if (negate) {
            return -fraction;
        } else {
            return fraction;
        }
    }


    private Duration parse(String text) {
        Matcher matcher = PATTERN.matcher(text);
        if (!matcher.matches()) {
            throw new DateTimeParseException("Text cannot be parsed to a Duration", text, 0);
        }

        boolean negate = isNegative(text, matcher.start(1), matcher.end(1));
        int yearStart = matcher.start(2), yearEnd = matcher.end(2);
        int monthStart = matcher.start(3), monthEnd = matcher.end(3);
        int dayStart = matcher.start(4), dayEnd = matcher.end(4);
        int hourStart = matcher.start(6), hourEnd = matcher.end(6);
        int minuteStart = matcher.start(7), minuteEnd = matcher.end(7);
        int secondStart = matcher.start(8), secondEnd = matcher.end(8);
        int fractionStart = matcher.start(9), fractionEnd = matcher.end(9);

        int years = 0;
        int months = 0;
        int days = 0;
        long hoursAsSecs = 0;
        long minutesAsSecs = 0;
        long seconds = 0;
        long nanos = 0;

        if (yearStart >= 0) {
            years = Integer.parseInt(text.substring(yearStart, yearEnd));
        }

        if (monthStart >= 0) {
            months = Integer.parseInt(text.substring(monthStart, monthEnd));
        }

        if (dayStart >= 0) {
            days = Integer.parseInt(text.substring(dayStart, dayEnd));
        }

        if (hourStart >= 0) {
            hoursAsSecs = Math.multiplyExact(3600L, Integer.parseInt(text.substring(hourStart, hourEnd)));
        }

        if (minuteStart >= 0) {
            minutesAsSecs = Math.multiplyExact(60L, Integer.parseInt(text.substring(minuteStart, minuteEnd)));
        }

        if (secondStart >= 0) {
            seconds = Integer.parseInt(text.substring(secondStart, secondEnd), 10);
            boolean negativeSecs = text.charAt(secondStart) == '-';
            nanos = parseFraction(text, fractionStart, fractionEnd, negativeSecs);
        }

        long secondsTotal = Math.addExact(hoursAsSecs, Math.addExact(minutesAsSecs, seconds));

        Duration duration = Duration.ofSeconds(secondsTotal, nanos).plus(estimateDuration(years, months, days));

        if (negate) {
            return duration.negated();
        }

        return duration;
    }


    @Override
    public Duration deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String text = p.getText();

        try {
            return parse(text);
        } catch (Exception e) {
            ctxt.reportWrongTokenException(this, p.currentToken(), "Text cannot be parsed to a Duration");
        }

        return null;
    }
}

package io.lindstrom.mpd.data;

import java.util.Objects;

public class FrameRate {
    private final long numerator;
    private final Long denominator;

    public FrameRate(long numerator, Long denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public long getNumerator() {
        return numerator;
    }

    public Long getDenominator() {
        return denominator;
    }

    @Override
    public String toString() {
        return "FrameRate{" + numerator + (denominator == null ? "" : ("/" + denominator)) + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameRate frameRate = (FrameRate) o;
        return numerator == frameRate.numerator &&
                Objects.equals(denominator, frameRate.denominator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }
}

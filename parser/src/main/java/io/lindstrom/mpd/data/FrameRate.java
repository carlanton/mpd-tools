package io.lindstrom.mpd.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class FrameRate {
    private final long numerator;
    private final Long denominator;

    public FrameRate(long numerator, Long denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public FrameRate(long frameRate) {
        this(frameRate, null);
    }

    public long getNumerator() {
        return numerator;
    }

    public Long getDenominator() {
        return denominator;
    }

    @JsonIgnore
    public double toDouble() {
        if (denominator == null) {
            return numerator;
        } else {
            return numerator / (double) denominator;
        }
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

    public Builder buildUpon() {
        return new Builder()
                .withNumerator(numerator)
                .withDenominator(denominator);
    }

    public static class Builder {
        private long numerator;
        private Long denominator;

        public Builder withNumerator(long numerator) {
            this.numerator = numerator;
            return this;
        }

        public Builder withDenominator(Long denominator) {
            this.denominator = denominator;
            return this;
        }

        public FrameRate build() {
            return new FrameRate(numerator, denominator);
        }
    }
}

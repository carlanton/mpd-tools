package io.lindstrom.mpd.validator;

import java.util.Objects;

public class Violation {
    private static final Violation EMPTY_VIOLATION = new Violation(null, null);

    private final String identifier;
    private final String message;

    public Violation(String identifier, String message) {
        this.identifier = identifier;
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Violation violation = (Violation) o;
        return Objects.equals(identifier, violation.identifier) &&
                Objects.equals(message, violation.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, message);
    }

    @Override
    public String toString() {
        return "Violation{" +
                "identifier='" + identifier + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getMessage() {
        return message;
    }

    public static Violation empty() {
        return EMPTY_VIOLATION;
    }
}

package io.lindstrom.mpd;

import io.lindstrom.mpd.validator.Violation;

import java.util.List;

public class ManifestValidationException extends Exception {
    private final List<Violation> violations;

    public ManifestValidationException(String message, List<Violation> violations) {
        super(message);
        this.violations = violations;
    }

    public List<Violation> getViolations() {
        return violations;
    }
}

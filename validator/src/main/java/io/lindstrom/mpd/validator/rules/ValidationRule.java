package io.lindstrom.mpd.validator.rules;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface ValidationRule {
    String value();
}

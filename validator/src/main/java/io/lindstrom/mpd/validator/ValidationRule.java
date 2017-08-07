package io.lindstrom.mpd.validator;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface ValidationRule {
    String value();
}

package io.lindstrom.mpd.v2;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum PresentationType {
    @JsonProperty("static")
    STATIC,

    @JsonProperty("dynamic")
    DYNAMIC,

    @JsonEnumDefaultValue
    INVALID
}
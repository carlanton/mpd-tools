package io.lindstrom.mpd.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PresentationType {
    @JsonProperty("static")
    STATIC,

    @JsonProperty("dynamic")
    DYNAMIC;
}

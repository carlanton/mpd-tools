package io.lindstrom.mpd.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PreselectionOrder {
    @JsonProperty("undefined") UNDEFINED,
    @JsonProperty("time-ordered") TIME_ORDERED,
    @JsonProperty("fully-ordered") FULLY_ORDERED
}
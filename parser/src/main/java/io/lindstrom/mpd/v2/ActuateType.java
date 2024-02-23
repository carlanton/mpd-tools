package io.lindstrom.mpd.v2;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ActuateType {
    @JsonProperty("onLoad") ON_LOAD,
    @JsonProperty("onRequest") ON_REQUEST
}
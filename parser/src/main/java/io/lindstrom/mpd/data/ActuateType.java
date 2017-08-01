package io.lindstrom.mpd.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ActuateType {
    @JsonProperty("onLoad") ON_LOAD,
    @JsonProperty("onRequest") ON_REQUEST;
}

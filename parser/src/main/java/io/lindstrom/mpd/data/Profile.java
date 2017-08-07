package io.lindstrom.mpd.data;

public enum Profile {
    /**
     * MPEG-DASH Full profile.
     */
    MPEG_DASH_FULL("urn:mpeg:dash:profile:full:2011"),

    /**
     * MPEG-DASH ISO Base media file format On Demand profile.
     */
    MPEG_DASH_ON_DEMAND("urn:mpeg:dash:profile:isoff-on-demand:2011"),

    /**
     * MPEG-DASH ISO Base media file format live profile.
     */
    MPEG_DASH_LIVE("urn:mpeg:dash:profile:isoff-live:2011"),

    /**
     * MPEG-DASH ISO Base media file format main profile.
     */
    MPEG_DASH_MAIN("urn:mpeg:dash:profile:isoff-main:2011"),

    /**
     * MPEG-DASH MPEG-2 TS main profile.
     */
    MPEG_DASH_MP2TS("urn:mpeg:dash:profile:mp2t-main:2011"),

    /**
     * 	MPEG-DASH MPEG-2 TS simple profile.
     */
    MPEG_DASH_MP2TS_SIMPLE("urn:mpeg:dash:profile:mp2t-simple:2011"),

    /**
     * 3GP-DASH Release-10 profile.
     */
    MPEG_DASH_3GP("urn:3GPP:PSS:profile:DASH10"),

    /**
     * HbbTV 2.0 DASH profiles.
     */
    HBBTV201("urn:dvb:dash:profile:dvb-dash:2014"),

    /**
     * HbbTV 1.5 DASH profiles.
     */
    HBBTV15("urn:hbbtv:dash:profile:isoff-live:2012");

    private final String identifier;

    Profile(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return identifier;
    }

    public static Profile fromIdentifier(String identifier) {
        for (Profile profile : values()) {
            if (profile.identifier.equals(identifier)) {
                return profile;
            }
        }
        throw new IllegalArgumentException();
    }
}

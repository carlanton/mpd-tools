# mpd-parser

[![Build Status](https://travis-ci.org/carlanton/mpd-tools.svg?branch=master)](https://travis-ci.org/carlanton/mpd-tools) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.lindstrom/mpd-parser/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.lindstrom/mpd-parser)

A simple MPEG DASH manifest (MPD) parser for Java.

## Usage

```java
MPDParser parser = new MPDParser();
MPD mpd;

// Read existing manifest
try (InputStream inputStream = Files.newInputStream(Paths.get("parser/src/test/resources/vectors/bbb_live_multires_10bit_hev.mpd"))) {
    mpd = parser.parse(inputStream);
}

// Let's remove all video representations below 2 mbit
Predicate<Representation> predicate = representation -> {
    if ("video/mp4".equals(representation.getMimeType())) {
        return representation.getBandwidth() >= 2000000;
    } else {
        return true; // keep all non-video representations
    }
};

MPD updatedMpd = mpd.buildUpon()
        .withPeriods(mpd.getPeriods().stream()
                .map(period -> period.buildUpon()
                        .withAdaptationSets(period.getAdaptationSets().stream()
                                .map(adaptationSet -> adaptationSet.buildUpon()
                                        .withRepresentations(adaptationSet.getRepresentations().stream()
                                                .filter(predicate)
                                                .collect(Collectors.toList()))
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList()))
        .build();

System.out.println(parser.writeAsString(updatedMpd));

```

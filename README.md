# mpd-tools
[![Build Status](https://travis-ci.org/carlanton/mpd-tools.svg?branch=master)](https://travis-ci.org/carlanton/mpd-tools) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.lindstrom/mpd-parser/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.lindstrom/mpd-parser)

This repo contains tools related to MPEG-DASH manifets (MPD).

## [Parser](https://github.com/carlanton/mpd-tools/tree/master/parser)
*status:* enterprise production grade quality

This is a parser and an object model for MPEG-DASH manifests. Not to be confused
with the jaxb/xsd based model, this one is handwritten with builders and
final fields etc. The goal is to provide a sane(r) interface when working with
MPDs.

It's based on Jackson's [XMLMapper](https://github.com/FasterXML/jackson-dataformat-xml/)

Version >= 0.7 is adjusted to work better with Java 10.

## Validator
*status:* PoC

This is a pure-Java port of the Schematron based mpdvalidator: https://github.com/Dash-Industry-Forum/Conformance-Software/tree/master/webfe/mpdvalidator

It's faster and hopefully easier to understand? ...


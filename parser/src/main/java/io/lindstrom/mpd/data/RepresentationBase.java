package io.lindstrom.mpd.data;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.lindstrom.mpd.data.descriptor.Descriptor;


import java.util.List;

public interface RepresentationBase {
    @JacksonXmlProperty(localName = "FramePacking", namespace = MPD.NAMESPACE)
    List<Descriptor> framePackings();

    @JacksonXmlProperty(localName = "AudioChannelConfiguration", namespace = MPD.NAMESPACE)
    List<Descriptor> audioChannelConfigurations();

    @JacksonXmlProperty(localName = "ContentProtection", namespace = MPD.NAMESPACE)
    List<Descriptor> contentProtections();

    @JacksonXmlProperty(localName = "EssentialProperty", namespace = MPD.NAMESPACE)
    List<Descriptor> essentialProperties();

    @JacksonXmlProperty(localName = "SupplementalProperty", namespace = MPD.NAMESPACE)
    List<Descriptor> supplementalProperties();

    @JacksonXmlProperty(localName = "InbandEventStream", namespace = MPD.NAMESPACE)
    List<EventStream> inbandEventStreams();

    @JacksonXmlProperty(isAttribute = true)
    String profiles();

    @JacksonXmlProperty(isAttribute = true)
    Long width();

    @JacksonXmlProperty(isAttribute = true)
    Long height();

    @JacksonXmlProperty(isAttribute = true)
    Ratio sar();

    @JacksonXmlProperty(isAttribute = true)
    FrameRate frameRate();

    @JacksonXmlProperty(isAttribute = true)
    String audioSamplingRate();

    @JacksonXmlProperty(isAttribute = true)
    String mimeType();

    @JacksonXmlProperty(isAttribute = true)
    String segmentProfiles();

    @JacksonXmlProperty(isAttribute = true)
    String codecs();

    @JacksonXmlProperty(isAttribute = true)
    Double maximumSAPPeriod();

    @JacksonXmlProperty(isAttribute = true)
    Long startWithSAP();

    @JacksonXmlProperty(isAttribute = true)
    Double maxPlayoutRate();

    @JacksonXmlProperty(isAttribute = true)
    Boolean codingDependency();

    @JacksonXmlProperty(isAttribute = true)
    VideoScanType scanType();

    @JacksonXmlProperty(isAttribute = true)
    Long selectionPriority();
}
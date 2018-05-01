package io.lindstrom.mpd.data;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.lindstrom.mpd.support.Utils;

import java.util.List;
import java.util.Objects;

public class EventStream {
    @JacksonXmlProperty(localName = "Event", namespace = MPD.NAMESPACE)
    private final List<Event> events;

    @JacksonXmlProperty(namespace = "http://www.w3.org/1999/xlink")
    private final String href;

    @JacksonXmlProperty(namespace = "http://www.w3.org/1999/xlink")
    private final ActuateType actuate;

    @JacksonXmlProperty(isAttribute = true)
    private final String schemeIdUri;

    @JacksonXmlProperty(isAttribute = true)
    private final String value;

    @JacksonXmlProperty(isAttribute = true)
    private final Long timescale;

    @JacksonXmlProperty(isAttribute = true)
    private final String messageData;

    private EventStream(List<Event> events, String href, ActuateType actuate, String schemeIdUri, String value, Long timescale, String messageData) {
        this.events = events;
        this.href = href;
        this.actuate = actuate;
        this.schemeIdUri = schemeIdUri;
        this.value = value;
        this.timescale = timescale;
        this.messageData = messageData;
    }

    @SuppressWarnings("unused")
    private EventStream() {
        this.events = null;
        this.href = null;
        this.actuate = null;
        this.schemeIdUri = null;
        this.value = null;
        this.timescale = null;
        this.messageData = null;
    }

    public List<Event> getEvents() {
        return Utils.unmodifiableList(events);
    }

    public String getHref() {
        return href;
    }

    public ActuateType getActuate() {
        return actuate;
    }

    public String getSchemeIdUri() {
        return schemeIdUri;
    }

    public String getValue() {
        return value;
    }

    public Long getTimescale() {
        return timescale;
    }

    public String getMessageData() {
        return messageData;
    }

    @Override
    public String toString() {
        return "EventStream{" +
                "events=" + events +
                ", href='" + href + '\'' +
                ", actuate=" + actuate +
                ", schemeIdUri='" + schemeIdUri + '\'' +
                ", value='" + value + '\'' +
                ", timescale=" + timescale +
                ", messageData='" + messageData + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventStream that = (EventStream) o;
        return Objects.equals(events, that.events) &&
                Objects.equals(href, that.href) &&
                actuate == that.actuate &&
                Objects.equals(schemeIdUri, that.schemeIdUri) &&
                Objects.equals(value, that.value) &&
                Objects.equals(timescale, that.timescale) &&
                Objects.equals(messageData, that.messageData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(events, href, actuate, schemeIdUri, value, timescale, messageData);
    }

    public Builder buildUpon() {
        return new Builder()
                .withEvents(events)
                .withHref(href)
                .withActuate(actuate)
                .withSchemeIdUri(schemeIdUri)
                .withValue(value)
                .withTimescale(timescale)
                .withMessageData(messageData);
    }

    public static class Builder {
        private List<Event> events;
        private String href;
        private ActuateType actuate;
        private String schemeIdUri;
        private String value;
        private Long timescale;
        private String messageData;

        public Builder withEvents(List<Event> events) {
            this.events = events;
            return this;
        }

        public Builder withHref(String href) {
            this.href = href;
            return this;
        }

        public Builder withActuate(ActuateType actuate) {
            this.actuate = actuate;
            return this;
        }

        public Builder withSchemeIdUri(String schemeIdUri) {
            this.schemeIdUri = schemeIdUri;
            return this;
        }

        public Builder withValue(String value) {
            this.value = value;
            return this;
        }

        public Builder withTimescale(Long timescale) {
            this.timescale = timescale;
            return this;
        }

        public Builder withMessageData(String messageData) {
            this.messageData = messageData;
            return this;
        }

        public EventStream build() {
            return new EventStream(events, href, actuate, schemeIdUri, value, timescale, messageData);
        }
    }
}

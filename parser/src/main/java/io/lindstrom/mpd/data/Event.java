package io.lindstrom.mpd.data;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import io.lindstrom.mpd.data.scte35.Scte35SpliceInfoSection;
import static io.lindstrom.mpd.MPDParser.SCTE35_NAMESPACE_URI;

import java.util.Objects;

public class Event {
    @JacksonXmlProperty(isAttribute = true)
    private final Long presentationTime;

    @JacksonXmlProperty(isAttribute = true)
    private final Long duration;

    @JacksonXmlProperty(isAttribute = true)
    private final Long id;

    @JacksonXmlProperty(isAttribute = true)
    private final String messageData;

    @JacksonXmlText
    private final String textContent;
    
    @JacksonXmlProperty(localName = "SpliceInfoSection", namespace = SCTE35_NAMESPACE_URI)
    private final Scte35SpliceInfoSection spliceInfoSection;

    private Event(Long presentationTime, Long duration, Long id, String messageData, 
                 String textContent, Scte35SpliceInfoSection spliceInfoSection) {
        this.presentationTime = presentationTime;
        this.duration = duration;
        this.id = id;
        this.messageData = messageData;
        this.textContent = textContent;
        this.spliceInfoSection = spliceInfoSection;
    }

    @SuppressWarnings("unused")
    private Event() {
        this.presentationTime = null;
        this.duration = null;
        this.id = null;
        this.messageData = null;
        this.textContent = null;
        this.spliceInfoSection = null;
    }

    public Long getPresentationTime() {
        return presentationTime;
    }

    public Long getDuration() {
        return duration;
    }

    public Long getId() {
        return id;
    }

    public String getMessageData() {
        return messageData;
    }

    public String getTextContent() {
        return textContent;
    }

    public Scte35SpliceInfoSection getSpliceInfoSection() {
        return spliceInfoSection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(presentationTime, event.presentationTime) &&
                Objects.equals(duration, event.duration) &&
                Objects.equals(id, event.id) &&
                Objects.equals(messageData, event.messageData) &&
                Objects.equals(textContent, event.textContent) &&
                Objects.equals(spliceInfoSection, event.spliceInfoSection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(presentationTime, duration, id, messageData, textContent, spliceInfoSection);
    }

    @Override
    public String toString() {
        return "Event{" +
                "presentationTime=" + presentationTime +
                ", duration=" + duration +
                ", id=" + id +
                ", messageData='" + messageData + '\'' +
                ", textContent='" + textContent + '\'' +
                ", spliceInfoSection=" + spliceInfoSection +
                '}';
    }

    public Builder buildUpon() {
        return new Builder()
                .withPresentationTime(presentationTime)
                .withDuration(duration)
                .withId(id)
                .withMessageData(messageData)
                .withTextContent(textContent)
                .withSpliceInfoSection(spliceInfoSection);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long presentationTime;
        private Long duration;
        private Long id;
        private String messageData;
        private String textContent;
        private Scte35SpliceInfoSection spliceInfoSection;

        public Builder withPresentationTime(Long presentationTime) {
            this.presentationTime = presentationTime;
            return this;
        }

        public Builder withDuration(Long duration) {
            this.duration = duration;
            return this;
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withMessageData(String messageData) {
            this.messageData = messageData;
            return this;
        }

        public Builder withTextContent(String textContent) {
            this.textContent = textContent;
            return this;
        }

        public Builder withSpliceInfoSection(Scte35SpliceInfoSection spliceInfoSection) {
            this.spliceInfoSection = spliceInfoSection;
            return this;
        }

        public Event build() {
            return new Event(presentationTime, duration, id, messageData, textContent, spliceInfoSection);
        }
    }
}


package io.lindstrom.mpd.data;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.Objects;

public class Event {
    @XmlAttribute(name = "presentationTime")
    private final Long presentationTime;

    @XmlAttribute(name = "duration")
    private final Long duration;

    @XmlAttribute(name = "id")
    private final Long id;

    @XmlAttribute(name = "messageData")
    private final String messageData;

    private Event(Long presentationTime, Long duration, Long id, String messageData) {
        this.presentationTime = presentationTime;
        this.duration = duration;
        this.id = id;
        this.messageData = messageData;
    }

    @SuppressWarnings("unused")
    private Event() {
        this.presentationTime = null;
        this.duration = null;
        this.id = null;
        this.messageData = null;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(presentationTime, event.presentationTime) &&
                Objects.equals(duration, event.duration) &&
                Objects.equals(id, event.id) &&
                Objects.equals(messageData, event.messageData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(presentationTime, duration, id, messageData);
    }

    @Override
    public String toString() {
        return "Event{" +
                "presentationTime=" + presentationTime +
                ", duration=" + duration +
                ", id=" + id +
                ", messageData='" + messageData + '\'' +
                '}';
    }

    public Builder buildUpon() {
        return new Builder()
                .withPresentationTime(presentationTime)
                .withDuration(duration)
                .withId(id)
                .withMessageData(messageData);
    }

    public static class Builder {
        private Long presentationTime;
        private Long duration;
        private Long id;
        private String messageData;

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

        public Event build() {
            return new Event(presentationTime, duration, id, messageData);
        }
    }
}

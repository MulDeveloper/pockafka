package dev.muldev.pockafka.events;


import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public abstract class Event {
    private String eventId;
    private String occurredOn;

    public Event() {
        this.eventId     = UUID.randomUUID().toString();
        this.occurredOn  = Date.from(Instant.now()).toString();
    }

    public Event(String eventId, String occurredOn) {
        this.eventId     = eventId;
        this.occurredOn  = occurredOn;
    }

    public String eventId() {
        return eventId;
    }

    public String occurredOn() {
        return occurredOn;
    }
}

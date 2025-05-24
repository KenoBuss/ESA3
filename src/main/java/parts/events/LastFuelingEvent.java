package parts.events;

import javafx.event.Event;
import javafx.event.EventType;

import java.io.Serializable;

public class LastFuelingEvent extends Event implements Serializable {


    private static final long serialVersionUID = 1L;
    public static final EventType<LastFuelingEvent> LASTFUELING = new EventType<>(Event.ANY, "LASTFUELING");
    public static final EventType<LastFuelingEvent> ANY = new EventType<>(LASTFUELING, "ANY");
    public static final EventType<LastFuelingEvent> LASTFUELING_CLICKED = new EventType<>(ANY, "LASTFUELING_CLICKED");


    public LastFuelingEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }
}

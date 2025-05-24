package parts.events;

import javafx.event.Event;
import javafx.event.EventType;

import java.io.Serializable;

public class TotalAverageEvent extends Event implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final EventType<TotalAverageEvent> TOTALAVERAGE = new EventType<>(Event.ANY, "TOTALAVERAGE");
    public static final EventType<TotalAverageEvent> ANY = new EventType<>(TOTALAVERAGE, "ANY");
    public static final EventType<TotalAverageEvent> TOTALAVERAGE_CLICKED = new EventType<>(ANY, "TOTALAVERAGE_CLICKED");


    public TotalAverageEvent (EventType<? extends Event> eventType) {
        super(eventType);
    }
}

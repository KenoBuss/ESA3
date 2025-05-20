package parts.events;

import javafx.event.Event;
import javafx.event.EventType;
import parts.Entry;

import java.io.Serializable;

public class EntryEvent extends Event implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final EventType<EntryEvent> ENTRY = new EventType<>(Event.ANY, "ENTRY");
    public static final EventType<EntryEvent> ANY = new EventType<>(ENTRY, "ANY");
    public static final EventType<EntryEvent> ENTRY_SAVE = new EventType<>(ANY, "ENTRY_SAVE");


    private final Entry entry;

    public EntryEvent(EventType<? extends Event> eventType, Entry entry) {
        super(eventType);
        this.entry = entry;
    }

    public Entry getEntry() {
        return this.entry;
    }
}

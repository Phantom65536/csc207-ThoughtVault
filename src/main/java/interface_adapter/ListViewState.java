package interface_adapter;

import java.util.HashMap;

public class ListViewState {
    private HashMap<Integer, HashMap<String, ?>> events;
    private HashMap<Integer, HashMap<String, ?>> notes;

    public ListViewState() {
        events = new HashMap<Integer, HashMap<String, ?>>();

        notes = new HashMap<Integer, HashMap<String, ?>>();
    }

    public HashMap<Integer, HashMap<String, ?>> getEvents() {
        return events;
    }

    public HashMap<Integer, HashMap<String, ?>> getNotes() {
        return notes;
    }

    public void setEvents(HashMap<Integer, HashMap<String, ?>> events) {
        this.events = events;
    }

    public void setNotes(HashMap<Integer, HashMap<String, ?>> notes) {
        this.notes = notes;
    }
}

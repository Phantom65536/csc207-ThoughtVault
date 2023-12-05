package interface_adapter;

import java.util.ArrayList;

public class NoteState {
    private String title;

    private String location;

    private String description;

    private boolean isWork;

    private boolean pinned;

    private ArrayList<Integer> subEvents;

    public NoteState() {
    }

    public NoteState(String title, String location, String description, boolean
            isWork, boolean pinned, ArrayList<Integer> subEvents) {
        this.title = title;

        this.location = location;

        this.description = description;

        this.isWork = isWork;

        this.pinned = pinned;

        this.subEvents = subEvents;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsWork() {
        return this.isWork;
    }

    public boolean getPinned() {
        return this.pinned;
    }

    public ArrayList<Integer> getSubEvents() {
        return subEvents;
    }
}

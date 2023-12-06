package interface_adapter.note;

import java.util.ArrayList;

public class NoteState {
    private int userId;
    private String title;

    private String location;

    private String description;

    private boolean isWork;

    private boolean pinned;

    private int id;
    private ArrayList<Integer> subEvents;

    public NoteState() {
    }

    public NoteState(int id, String title, String location, String description, boolean
            isWork, boolean pinned, ArrayList<Integer> subEvents, int userId) {
        this.title = title;
        this.id = id;
        this.location = location;

        this.description = description;

        this.isWork = isWork;

        this.pinned = pinned;

        this.subEvents = subEvents;

        this.userId = userId;
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
    public int getUserId(){return this.userId;}
    public int getId(){return this.id;}
    public void setUserId(int userId){this.userId = userId;}
    public ArrayList<Integer> getSubEvents() {
        return subEvents;
    }
}

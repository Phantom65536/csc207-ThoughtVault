package use_case.note;

import java.util.ArrayList;

public class NoteInputData {
    private final int ID;

    private final String title;

    private final int userID;

    private final String location;

    private final String description;

    private final boolean isWork;

    private final boolean pinned;

    private final ArrayList<Integer> subEvents;

    public NoteInputData (int ID, String title, int userID, String location,
                          String description, boolean isWork, boolean pinned,
                          ArrayList<Integer> subEvents) {
        this.ID = ID;

        this.title = title;

        this.userID = userID;

        this.location = location;

        this.description = description;

        this.pinned = pinned;

        this.isWork = isWork;

        this.subEvents = subEvents;
    }

    public int getID() {
        return ID;
    }

    public String getDescription() {
        return description;
    }

    public int getUserID() {
        return userID;
    }

    public String getLocation() {
        return location;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Integer> getSubEvents() {
        return subEvents;
    }

    public boolean getPinned() {
        return pinned;
    }

    public boolean getIsWork() {
        return isWork;
    }
}

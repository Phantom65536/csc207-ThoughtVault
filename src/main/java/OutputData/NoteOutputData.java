package OutputData;

import java.util.ArrayList;
import java.util.HashMap;

public class NoteOutputData {
    private final int ID;

    private final String title;

    private final int userID;

    private final String location;

    private final String description;

    private final boolean isWork;

    private final boolean pinned;

    private final ArrayList<Integer> subEvents;

    public NoteOutputData(int id, String title, int userID, String location,
                          String description, boolean isWork, boolean pinned,
                          ArrayList<Integer> subEvents) {
        this.ID = id;

        this.title = title;

        this.userID = userID;

        this.location = location;

        this.description = description;

        this.isWork = isWork;

        this.pinned = pinned;

        this.subEvents = subEvents;
    }

    public HashMap<String, Object> getNoteData() {
        HashMap<String, Object> data = new HashMap<String, Object>();

        data.put("title", getTitle());

        data.put("isWork", getIsWork());

        data.put("pinned", getPinned());

        return data;
    }

    public boolean getPinned() {
        return pinned;
    }

    public boolean getIsWork() {
        return isWork;
    }

    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Integer> getSubEvents() {
        return subEvents;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }
}

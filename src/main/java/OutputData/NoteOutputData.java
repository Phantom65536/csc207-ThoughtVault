package OutputData;

import java.util.ArrayList;

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
}

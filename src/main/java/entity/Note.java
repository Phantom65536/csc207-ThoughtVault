package entity;

import java.util.ArrayList;

public class Note implements NoteInterface {
    private final int ID;
    private String title;
    private final int userID;
    private String location;
    private String description;
    private LabelCat label;
    private boolean pinned;
    private ArrayList<Integer> descendantsID;           // make sure the descendant IDs are valid when instantiating a Note

    public Note(int ID, String title, int userID, String location, String description, boolean isWork, boolean pinned,
                ArrayList<Integer> descendants) {
        this.ID = ID;
        this.userID = userID;
        amendAllAttributes(title, location, description, isWork, pinned, descendants);
    }

    public void amendAllAttributes(String title, String location, String description, boolean isWork, boolean pinned,
                                   ArrayList<Integer> descendants) {
        this.title = title;
        this.location = location;
        this.description = description;
        this.pinned = pinned;
        label = isWork ? LabelCat.WORK : LabelCat.PERSONAL;
        this.descendantsID = new ArrayList<>(descendants);
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getUserID() {
        return userID;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isWork() {
        return label == LabelCat.WORK;
    }

    @Override
    public boolean getPinned() {
        return pinned;
    }

    @Override
    public ArrayList<Integer> getDescendants() {
        return descendantsID;
    }

    @Override
    public void removeDescendants(int descendantID) {
        descendantsID.remove(descendantID);
    }
}

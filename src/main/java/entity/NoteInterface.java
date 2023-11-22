package entity;

import java.util.ArrayList;

public interface NoteInterface {
    int getID();
    String getTitle();
    int getUserID();
    String getLocation();
    String getDescription();
    boolean isWork();
    boolean getPinned();
    ArrayList<Integer> getDescendants();
    void removeDescendants(int descendantID);
}

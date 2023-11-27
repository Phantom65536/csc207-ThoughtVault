package InputData;

import entity.LabelCat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class EventInputData {
    private final int ID;
    private String title;
    private final int userID;
    private String location;
    private String description;
    private LabelCat label;
    private boolean pinned;
    private ArrayList<Integer> descendantsID;
    private LocalDate date;
    private LocalTime startTime = LocalTime.MIDNIGHT;                 // default starttime when user specifies whole-day
    private LocalTime endTime = LocalTime.of(23, 59);
    public EventInputData (int ID, String title, int userID, String location,
                           String description, LabelCat label, boolean pinned,
                           ArrayList<Integer> descendantsID, LocalDate date,
                           LocalTime startTime, LocalTime endTime) {
        this.ID = ID;
        this.title = title;
        this.userID = userID;
        this.location = location;
        this.description = description;
        this.label = label;
        this.pinned = pinned;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public LabelCat getLabel() {
        return label;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public ArrayList<Integer> getDescendantsID() {
        return descendantsID;
    }
    public boolean getPinned() {
        return pinned;
    }
}

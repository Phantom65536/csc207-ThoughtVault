package entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class LocalEvent implements LocalEventInterface {
    private final int ID;
    private String title;
    private final int userID;
    private LocalDate date;
    private LocalTime startTime = LocalTime.MIDNIGHT;
    private LocalTime endTime = LocalTime.of(23, 59);
    private String location;
    private String description;
    public enum labelCat {WORK, PERSONAL};
    private labelCat label;
    private boolean pinned;
    private ArrayList<Integer> subEventsID;


    public LocalEvent(int ID, String title, int userID, LocalDate date, LocalTime startTime, LocalTime endTime,
                      String location, String description, boolean isWork, boolean pinned, ArrayList<Integer> subEvents) {
        this.ID = ID;
        this.userID = userID;
        amendAllAttributes(title, date, startTime, endTime, location, description, isWork, pinned, subEvents);
    }

//    public void editTitle(String title) { this.title = title; }
//
//    public void editDate(LocalDate date) { this.date = date; }
//
//    public void editStartTime(LocalTime startTime) { this.startTime = startTime; }
//
//    public void editEndTime(LocalTime endTime) { this.endTime = endTime; }
//
//    public void editLocation(String location) { this.location = location; }
//
//    public void editDescription(String description) { this.description = description; }
//
//    public void editLabel(boolean isWork) { label = isWork ? labelCat.WORK : labelCat.PERSONAL; }

    public void removeSubEvents(int subEventIDindex) {
        subEventsID.remove(subEventIDindex);
    }

    public void amendAllAttributes(String title, LocalDate date, LocalTime startTime, LocalTime endTime,
                                   String location, String description, boolean isWork, boolean pinned, ArrayList<Integer> subEvents) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.title = title;
        this.date = date;
        this.location = location;
        this.description = description;
        this.pinned = pinned;
        label = isWork ? labelCat.WORK : labelCat.PERSONAL;
        this.subEventsID = new ArrayList<>(subEvents);
    }

    public int getID() { return ID; }

    public String getTitle() { return title; }

    public int getUserID() { return userID; }

    public LocalDate getDate() { return date; }

    public LocalTime[] getStartEndTIme() { return new LocalTime[] {startTime, endTime}; }

    public String getLocation() { return location; }

    public String getDescription() { return description; }

    public boolean isWork() { return label == labelCat.WORK; }

    public boolean getPinned() { return pinned; }

    public ArrayList<Integer> getSubEvents() {return subEventsID; }

}

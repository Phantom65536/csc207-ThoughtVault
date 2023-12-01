package entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Event extends Note implements EventInterface {
    private String title;
    private final String userID;
    private LocalDate date;
    private LocalTime startTime = LocalTime.MIDNIGHT;
    private LocalTime endTime = LocalTime.of(23, 59);
    private String location;
    private String description;
    public enum labelCat {WORK, PERSONAL};
    private labelCat label;
    private boolean pinned;
    private ArrayList<String> subEventsID;


    public Event(String title, String userID, LocalDate date, LocalTime
            startTime, LocalTime endTime, String location, String description,
                 boolean isWork, boolean pinned, ArrayList<String> subEvents) {
        super(title, userID, date, description, isWork, pinned);
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.subEventsID = subEvents;
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

    public void removeSubEvents(int subEventIDIndex) {
        subEventsID.remove(subEventIDIndex);
    }

    public LocalTime[] getStartEndTime() { return new LocalTime[] {startTime,
            endTime}; }

    public String getLocation() { return location; }

    public ArrayList<String> getSubEvents() {return subEventsID; }

}

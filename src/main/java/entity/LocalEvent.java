package entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class LocalEvent extends Note implements LocalEventInterface {
    private LocalDate date;
    private LocalTime startTime = LocalTime.MIDNIGHT;                 // default starttime when user specifies whole-day
    private LocalTime endTime = LocalTime.of(23, 59);    // default starttime when user specifies whole-day


    public LocalEvent(int ID, String title, int userID, LocalDate date, LocalTime startTime, LocalTime endTime,
                      String location, String description, boolean isWork, boolean pinned, ArrayList<Integer> subEvents) {
        super(ID, title, userID, location, description, isWork, pinned, subEvents);
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
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

    public void amendAllAttributes(String title, LocalDate date, LocalTime startTime, LocalTime endTime,
                                   String location, String description, boolean isWork, boolean pinned, ArrayList<Integer> subEvents) {
        super.amendAllAttributes(title, location, description, isWork, pinned, subEvents);
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
    }

    public LocalDate getDate() { return date; }

    public LocalTime[] getStartEndTIme() { return new LocalTime[] {startTime, endTime}; }

}

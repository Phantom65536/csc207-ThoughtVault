package entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class LocalEvent implements LocalEventInterface {
    private final int ID;
    private String title;
    private final UserInterface user;
    private LocalDate date;
    private LocalTime startTime = LocalTime.MIDNIGHT;
    private LocalTime endTime = LocalTime.of(23, 59);
    private String location;
    private String description;
    private enum labelCat {WORK, PERSONAL};
    private labelCat label;
    private boolean pinned;
    private ArrayList<LocalEvent> subEvents;


    public LocalEvent(int ID, String title, UserInterface user, LocalDate date, LocalTime startTime, LocalTime endTime,
                      String location, String description, boolean isWork, boolean pinned, ArrayList<LocalEvent> subEvents) {
        this.ID = ID;
        this.user = user;
        amendAllAttributes(title, date, startTime, endTime, location, description, isWork, pinned, subEvents);
    }

    public void amendAllAttributes(String title, LocalDate date, LocalTime startTime, LocalTime endTime,
                                   String location, String description, boolean isWork, boolean pinned, ArrayList<LocalEvent> subEvents) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.title = title;
        this.date = date;
        this.location = location;
        this.description = description;
        this.pinned = pinned;
        label = isWork ? labelCat.WORK : labelCat.PERSONAL;
        this.subEvents = subEvents;
    }

    @Override
    public HashMap<String, Object> getAllAttributes() {
        HashMap<String, Object> res = new HashMap<>();
        res.put("ID", ID);
        res.put("title", title);
        res.put("date", date);
        res.put("startTime", startTime);
        res.put("endTime", endTime);
        res.put("location", location);
        res.put("description", description);
        res.put("label", label);
        res.put("pinned", pinned);
        res.put("sub-events", subEvents);
        return res;
    }
}

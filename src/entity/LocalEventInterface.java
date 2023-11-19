package entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public interface LocalEventInterface {
    int getID();
    String getTitle();
    int getUserID();
    LocalDate getDate();
    LocalTime[] getStartEndTIme();
    String getLocation();
    String getDescription();
    boolean isWork();
    boolean getPinned();
    ArrayList<Integer> getSubEvents();
    void amendAllAttributes(String title, LocalDate date, LocalTime startTime, LocalTime endTime,
                            String location, String description, boolean isWork, boolean pinned, ArrayList<Integer> subEvents);
}

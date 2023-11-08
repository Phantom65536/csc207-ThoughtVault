package entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public interface LocalEventInterface {
    HashMap<String, Object> getAllAttributes();
    void amendAllAttributes(String title, LocalDate date, LocalTime startTime, LocalTime endTime,
                            String location, String description, boolean isWork, boolean pinned, ArrayList<LocalEvent> subEvents);
}

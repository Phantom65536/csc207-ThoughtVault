package entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public interface EventInterface {
    LocalTime[] getStartEndTime();
    String getLocation();
    String getDescription();
    ArrayList<String> getSubEvents();
}

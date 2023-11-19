package entity;

import com.google.api.services.calendar.Calendar;

import java.util.HashMap;
import java.util.Map;

public class User {
    private final int id;
    private final String name;

    private final String calendarId;

    public User(int id, String name, String calendarId) {
        this.id = id;
        this.name = name;
        this.calendarId = calendarId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCalendarId() {
        return calendarId;
    }

    public Map<String, Object> getAllAttributes() {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("id", id);
        attributes.put("name", name);
        attributes.put("calendarId", calendarId);
        return attributes;
    }
}

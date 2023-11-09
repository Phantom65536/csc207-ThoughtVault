package entity;

import com.google.api.services.calendar.Calendar;

import java.util.HashMap;
import java.util.Map;

public class User {
    private final int id;
    private final String name;
    private final Calendar calendar;

    public User(int id, String name, Calendar calendar) {
        this.id = id;
        this.name = name;
        this.calendar = calendar;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public Map<String, Object> getAllAttributes() {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("id", id);
        attributes.put("name", name);
        attributes.put("calendar", calendar);
        return attributes;
    }
}

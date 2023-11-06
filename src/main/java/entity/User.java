package entity;

import com.google.api.services.calendar.Calendar;

public class User {
    private final int id;
    private final String name;
    private final Calendar calendar;

    public User(int id, String name, Calendar calendar) {
        this.id = id;
        this.name = name;
        this.calendar = calendar;
    }
}

package data_access;

import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import use_case.gcalevent.GCalEventDataAccessInterface;

public class GCalDataAccessObject implements GCalEventDataAccessInterface {
    private final Calendar calendar;
    private final String calendarId;

    public GCalDataAccessObject(Calendar calendar, Event event, String calendarId, String eventId) {
        this.calendar = calendar;
        this.calendarId = calendarId;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public String getCalendarId() {
        return calendarId;
    }
}

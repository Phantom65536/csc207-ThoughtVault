package use_case.gcalevent;

import com.google.api.services.calendar.Calendar;

public interface GCalEventDataAccessInterface {
    Calendar getCalendar();
    String getCalendarId();
}

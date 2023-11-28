package use_case.gcalevent;

import com.google.api.services.calendar.Calendar;

import java.io.IOException;

public interface GCalEventDataAccessInterface {
    Calendar getCalendar();
    String getCalendarId();
    boolean eventExists(String eventId) throws IOException;
}

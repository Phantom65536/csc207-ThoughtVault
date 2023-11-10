package use_case.gcalevent;

import com.google.api.services.calendar.Calendar;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface GCalEventDataAccessInterface {
    Calendar getCalendar();
    String getCalendarId();
    boolean eventExists(String eventId) throws IOException;
    Calendar createCalendar() throws GeneralSecurityException, IOException;
}

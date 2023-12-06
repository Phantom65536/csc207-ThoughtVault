package use_case.gcalevent;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.calendar.Calendar;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface GCalEventDataAccessInterface {
    public void resetUserCalendar();
    public void setUserCalendar(Credential credential) throws GeneralSecurityException, IOException;
    Calendar getCalendar();
    String getCalendarId();
    boolean eventExists(String eventId) throws IOException;
}

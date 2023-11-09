package use_case.gcalevent;

import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;

import java.io.IOException;

public class GCalEventOutputData {
    final private String eventId;

    public GCalEventOutputData(String eventId) {
        this.eventId = eventId;
    }

    public Event getEventById(Calendar calendar, String calendarId, String eventId) throws IOException {
        return calendar.events().get(calendarId, eventId).execute();
    }
}

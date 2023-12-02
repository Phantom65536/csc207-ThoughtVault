package use_case.gcalevent;

import com.google.api.services.calendar.model.Event;

import java.io.IOException;
import java.util.ArrayList;

public interface GCalEventInputBoundary {
    boolean importEvent(String eventId) throws IOException;
    boolean exportEvent(int localEventId) throws IOException;
    ArrayList<Event> getAllEvents() throws IOException;
}

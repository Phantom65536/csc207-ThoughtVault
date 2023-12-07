package use_case.gcalevent;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Input Data class for importing and exporting events.
 */
public class GCalEventInputData {
    final private String eventId;
    final private String title;
    final private DateTime date;
    final private DateTime startTime;
    final private DateTime endTime;
    final private String location;
    final private String description;

    /**
     * Instantiates an instance of GCalEventInputData
     * @param eventId The specific event identifier
     * @param calendar The user's Google Calendar
     * */
    public GCalEventInputData(String eventId, Calendar calendar) throws IOException {
        this.eventId = eventId;

        HashMap<String, Object> eventAttributes = getEventAttributes(calendar, eventId);
        this.title = (String) eventAttributes.get("title");
        this.date = (DateTime) eventAttributes.get("date");
        this.startTime = (DateTime) eventAttributes.get("startTime");
        this.endTime = (DateTime) eventAttributes.get("endTime");
        this.location = (String) eventAttributes.get("location");
        this.description = (String) eventAttributes.get("description");
    }

    /**
     * Get the eventId of the GCalInputData object
     * */
    public String getEventId() {
        return eventId;
    }

    /**
     * Get all of the event attributes of a specific event given a user's google calendar and the specific eventId
     * @param eventId The specific event identifier
     * @param calendar The user's Google Calendar
     * */
    private HashMap<String, Object> getEventAttributes(Calendar calendar, String eventId) throws IOException {
        CalendarList calendarList = calendar.calendarList().list().setPageToken(null).execute();
        List<CalendarListEntry> items = calendarList.getItems();
        String calendarId = items.get(0).getId();

        Event event = calendar.events().get(calendarId, eventId).execute();

        HashMap<String, Object> eventAttributes = new HashMap<>();
        eventAttributes.put("title", event.getSummary());
        eventAttributes.put("date", event.getStart().getDate());
        eventAttributes.put("startTime", event.getStart().getDateTime());
        eventAttributes.put("endTime", event.getEnd().getDateTime());
        eventAttributes.put("location", event.getLocation());
        eventAttributes.put("description", event.getDescription());
        return eventAttributes;
    }

    /**
     * Get the event title
     * */
    public String getTitle() {
        return title;
    }
}

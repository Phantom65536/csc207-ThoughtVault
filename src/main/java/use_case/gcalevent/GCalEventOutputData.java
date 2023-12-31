package use_case.gcalevent;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class GCalEventOutputData {
    final private String eventId;
    final private String title;
    final private Date date;
    final private DateTime startTime;
    final private DateTime endTime;
    final private String location;
    final private String description;
    final private HashMap<String, Object> eventAttributes;

    public GCalEventOutputData(String eventId, Calendar calendar, String calendarId) throws IOException {
        this.eventId = eventId;

        this.eventAttributes = getEventAttributes(calendar, eventId, calendarId);
        this.title = (String) eventAttributes.get("title");
        this.date = (Date) eventAttributes.get("date");
        this.startTime = (DateTime) eventAttributes.get("startTime");
        this.endTime = (DateTime) eventAttributes.get("endTime");
        this.location = (String) eventAttributes.get("location");
        this.description = (String) eventAttributes.get("description");
    }

    public String getEventId() {
        return eventId;
    }

    public Date getDate() {
        return date;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public String getLocation() {
        return location;
    }

    public Event getEventById(Calendar calendar, String calendarId, String eventId) throws IOException {
        return calendar.events().get(calendarId, eventId).execute();
    }

    public HashMap<String, Object> getEventAttributes() {
        return eventAttributes;
    }

    private HashMap<String, Object> getEventAttributes(Calendar calendar, String eventId, String calendarId) throws IOException {

        Event event = calendar.events().get(calendarId, eventId).execute();

        HashMap<String, Object> eventAttributes = new HashMap<>();
        eventAttributes.put("title", event.getSummary());

        DateTime googleDateTime = new DateTime(event.getStart().getDateTime().toString());
        java.util.Date javaDate = new java.util.Date(googleDateTime.getValue());

        eventAttributes.put("date", javaDate);
        eventAttributes.put("startTime", event.getStart().getDateTime());
        eventAttributes.put("endTime", event.getEnd().getDateTime());
        eventAttributes.put("location", event.getLocation());
        eventAttributes.put("description", event.getDescription());
        return eventAttributes;
    }

    public String getTitle() {
        return title;
    }
}

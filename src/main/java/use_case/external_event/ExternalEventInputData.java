package use_case.external_event;

import com.google.api.services.calendar.Calendar;

public class ExternalEventInputData {
//    final private Calendar calendar;
//    final private String calendarId;
    final private String eventId;

    public ExternalEventInputData(String eventId) {
//        this.calendar = calendar;
//        this.calendarId = calendarId;
        this.eventId = eventId;
    }

    public String getEventId() {
        return eventId;
    }
}

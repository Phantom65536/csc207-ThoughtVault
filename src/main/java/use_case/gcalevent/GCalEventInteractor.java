package use_case.gcalevent;

import com.google.api.services.calendar.Calendar;
import use_case.external_event.ExternalEventInputBoundary;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import use_case.external_event.ExternalEventInputData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GCalEventInteractor implements ExternalEventInputBoundary {
    final GCalEventDataAccessInterface userDataAccessObject;
    final GCalEventOutputBoundary gCalEventPresenter;

    public GCalEventInteractor(GCalEventDataAccessInterface userDataAccessObject,
                               GCalEventOutputBoundary gCalEventPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.gCalEventPresenter = gCalEventPresenter;
    }

    @Override
    public boolean importEvent(ExternalEventInputData externalEventInputData) throws IOException {
        Calendar calendar = userDataAccessObject.getCalendar();
        String calendarId = userDataAccessObject.getCalendarId();
        String eventId = externalEventInputData.getEventId();

        if (!userDataAccessObject.eventExists(eventId)) {
            gCalEventPresenter.prepareFailView(eventId + " :Event does not exist in your calendar.");
            return false;
        } else {
            Event event = calendar.events().get(calendarId, eventId).execute();
            System.out.println(event.getSummary());

            GCalEventOutputData gCalEventOutputData = new GCalEventOutputData(eventId);
            gCalEventPresenter.prepareSuccessView(gCalEventOutputData);

            return true;
        }
    }

    @Override
    public boolean exportEvent(ExternalEventInputData externalEventInputData) throws IOException {
        Calendar calendar = userDataAccessObject.getCalendar();
        String calendarId = userDataAccessObject.getCalendarId();
        String eventId = externalEventInputData.getEventId();

        Event event = calendar.events().get(calendarId, eventId).execute();
        Event importedEvent = calendar.events().calendarImport(calendarId, event).execute();
        System.out.println(importedEvent.getId());

        GCalEventOutputData gCalEventOutputData = new GCalEventOutputData(eventId);
        gCalEventPresenter.prepareSuccessView(gCalEventOutputData);

        return true;
    }

    public ArrayList<Event> getAllEvents(ExternalEventInputData externalEventInputData) throws IOException {
        ArrayList<Event> listOfEvents = new ArrayList<>();
        Calendar calendar = userDataAccessObject.getCalendar();
        String calendarId = userDataAccessObject.getCalendarId();
        String pageToken = null;

        do {
            Events events = calendar.events().list(calendarId).setPageToken(pageToken).execute();
            List<Event> items = events.getItems();
            for (Event event : items) {
                listOfEvents.add(event);
                System.out.println(event.getSummary());
            }
            pageToken = events.getNextPageToken();
        } while (pageToken != null);

        return listOfEvents;
    }
}

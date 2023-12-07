package use_case.gcalevent;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import use_case.EntriesDataAccessInterface;
import entity.localEvent.LocalEvent;
import use_case.localEvent.LocalEventOutputData;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class GCalEventInteractor implements GCalEventInputBoundary {
    final GCalEventDataAccessInterface userDataAccessObject;
    final GCalEventOutputBoundary gCalEventPresenter;
    final EntriesDataAccessInterface entriesDataAccessObject;

    public GCalEventInteractor(GCalEventDataAccessInterface userDataAccessObject,
                               GCalEventOutputBoundary gCalEventPresenter,
                               EntriesDataAccessInterface entriesDataAccessObject) {
        this.userDataAccessObject = userDataAccessObject;
        this.gCalEventPresenter = gCalEventPresenter;
        this.entriesDataAccessObject = entriesDataAccessObject;
    }

    /**
     * Imports an event from a user's Google Calendar given the eventId.
     *
     * @param eventId The eventId associated with the Google Calendar Event.
     * @return True if the event is successfully imported.
     * @throws IOException if the event cannot be retrieved from the user's Google Calendar
     */
    @Override
    public boolean importEvent(String eventId) throws IOException {
        Calendar calendar = userDataAccessObject.getCalendar();
        String calendarId = userDataAccessObject.getCalendarId();


        if (!userDataAccessObject.eventExists(eventId)) {
            gCalEventPresenter.prepareFailView(eventId + " :Event does not exist in your calendar.");
            return false;
        } else {
            Event event = calendar.events().get(calendarId, eventId).execute();
            System.out.println(event.getSummary());

            GCalEventOutputData gCalEventOutputData = new GCalEventOutputData(eventId, calendar, calendarId);
            gCalEventPresenter.prepareSuccessView(gCalEventOutputData);

            return true;
        }

    }

    /**
     * Exports a LocalEvent to the user's Google Calendar.
     * Assume that there is only one calendar associated with each user.
     *
     * @param localEventId The localeventId associated with the local event that the user wants to export.
     * @return True if the event is successfully exported.
     * @throws IOException If the exportedEvent instance cannot be inserted into the user's Google Calendar.
     */
    @Override
    public boolean exportEvent(int localEventId) throws IOException {
        Calendar calendar = userDataAccessObject.getCalendar();
        String calendarId = userDataAccessObject.getCalendarId();
        LocalEvent localEvent = (LocalEvent) entriesDataAccessObject.getByID(localEventId);

        Event exportedEvent = new Event();
        // exportedEvent.setICalUID(calendarId);

        LocalDateTime startDateTime = LocalDateTime.of(localEvent.getDate(), localEvent.getStartTime());
        Date startDate = Date.from(startDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant());

        DateTime start = new DateTime(startDate, TimeZone.getTimeZone("UTC"));
        exportedEvent.setStart(new EventDateTime().setDateTime(start));

        LocalDateTime endDateTime = LocalDateTime.of(localEvent.getDate(), localEvent.getEndTime());
        Date endDate = Date.from(endDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant());

        DateTime end = new DateTime(endDate, TimeZone.getTimeZone("UTC"));
        exportedEvent.setEnd(new EventDateTime().setDateTime(end));

        exportedEvent.setDescription(localEvent.getDescription());
        exportedEvent.setSummary(localEvent.getTitle());
        exportedEvent.setLocation(localEvent.getLocation());


        exportedEvent = calendar.events().insert("primary", exportedEvent).execute();


        // exportedEvent = calendar.events().insert("primary", exportedEvent).execute();
        System.out.println(exportedEvent.getId());

        GCalEventOutputData gCalEventOutputData = new GCalEventOutputData(exportedEvent.getId(), calendar, calendarId);
        gCalEventPresenter.prepareSuccessView(gCalEventOutputData);

        return true;
    }

    /**
     * Returns a list of GCalEventInputData and prints out the titles of the events.
     * This is for the controller to retrieve a list of all the events so that the
     * user can select which event they want to import.
     *
     * @return An arraylist of GCalEventInputData (events)
     * @throws IOException if the function cannot return a list of events from the user's calendar.
     */
    public ArrayList<GCalEventInputData> getAllEvents() throws IOException {
        ArrayList<GCalEventInputData> listOfEvents = new ArrayList<>();
        Calendar calendar = userDataAccessObject.getCalendar();
        String calendarId = userDataAccessObject.getCalendarId();
        String pageToken = null;

        do {
            Events events = calendar.events().list(calendarId).setMaxResults(10).setPageToken(pageToken).execute();
            List<Event> items = events.getItems();
            for (Event event : items) {
                GCalEventInputData inputData = new GCalEventInputData(event.getId(), calendar);
                listOfEvents.add(inputData);
                System.out.println(event.getSummary());
            }
            // Cap the list's length at 10 to ensure that they can be fit inside the View
            if (listOfEvents.size() >= 10) {
                break;
            }
            pageToken = events.getNextPageToken();
        } while (pageToken != null);

        return listOfEvents;
    }

    /**
     * Returns a list of LocalEventOutputData and prints out the titles of the events.
     * This is for the controller to retrieve a list of all the events so that the
     * user can select which event they want to export.
     *
     * @return An arraylist of LocalEventOutputData (events)
     */
    public ArrayList<LocalEventOutputData> getAllLocalEvents() {
        ArrayList<LocalEventOutputData> listOfEvents = new ArrayList<>();
        ArrayList items = entriesDataAccessObject.getAllUserEntries(0);
        for (Object item : items) {
            if (item instanceof LocalEvent) {
                LocalEvent event = (LocalEvent) item;
                LocalEventOutputData inputData = new LocalEventOutputData(
                        event.getID(),
                        event.getTitle(),
                        event.getUserID(),
                        event.getDate(),
                        event.getStartTime(),
                        event.getEndTime(),
                        event.getLocation(),
                        event.getDescription(),
                        event.isWork(),
                        event.getPinned(),
                        event.getDescendants(),
                        (HashMap<Integer, String>) entriesDataAccessObject.getTitlesOfAllEntries(event.getUserID())
                );
                listOfEvents.add(inputData);
                System.out.println(inputData.getTitle());
            }
            // Cap the list's length at 10 to ensure that they can be fit inside the View
            if (listOfEvents.size() >= 10) {
                break;
            }
        }
        return listOfEvents;
    }

    /**
     * Switch to Home View
     * */
    public boolean switchToHome() {
        gCalEventPresenter.switchToHome();
        return true;
    }

}

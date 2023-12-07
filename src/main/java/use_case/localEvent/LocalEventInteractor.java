package use_case.localEvent;

import data_access.EventsDataAccessObject;

import entity.localEvent.LocalEvent;

import java.util.ArrayList;

/**
 * The interactor for local events
 */
public class LocalEventInteractor implements LocalEventInputBoundary {
    private final LocalEventOutputBoundary localEventOutputBoundary;

    private final EventsDataAccessObject eventsDataAccessObject;

    public LocalEventInteractor(EventsDataAccessObject eventsDataAccessObject,
                                LocalEventOutputBoundary
                                        localEventOutputBoundary){
        this.eventsDataAccessObject = eventsDataAccessObject;

        this.localEventOutputBoundary = localEventOutputBoundary;
    }

    /**
     * Displays the event detailed view
     * @param eventID The ID of the event to be displayed
     */
    @Override
    public void DisplayEventDetailedView(int eventID) {
        LocalEvent event = eventsDataAccessObject.getByID(eventID);

        localEventOutputBoundary.DisplayEventDetailedView(new
                LocalEventOutputData(event.getID(), event.getTitle(),
                event.getUserID(), event.getDate(), event.getStartTime(),
                event.getEndTime(), event.getLocation(), event.getDescription(),
                event.isWork(), event.getPinned(), event.getDescendants()));
    }

    /**
     * Creates an event and saves it to the database
     * @param eventInputData The input data for the event to be created
     */
    @Override
    public void CreateEvent(LocalEventInputData eventInputData) {
        try {
            LocalEvent event = new LocalEvent(eventsDataAccessObject.getNewID(), eventInputData.getTitle(), eventInputData.getUserID(),
                    eventInputData.getDate(), eventInputData.getStartTime(), eventInputData.getEndTime(), eventInputData.getLocation(),
                    eventInputData.getDescription(), eventInputData.getIsWork(), eventInputData.getPinned(), eventInputData.getSubEvents());

            eventsDataAccessObject.save(event);

            localEventOutputBoundary.UpdateEventsList(
                    new LocalEventOutputData(event.getID(),
                            event.getTitle(), event.getUserID(),
                            event.getDate(), event.getStartTime(),
                            event.getEndTime(), event.getLocation(),
                            event.getDescription(), event.isWork(),
                            event.getPinned(), event.getDescendants()));
        }

        catch (RuntimeException e) {
            localEventOutputBoundary.EventFailView(
                    "Event creation failed.");
        }
    }

    /**
     * Edits an event and saves it to the database
     * @param eventInputData The input data for the event to be edited
     */
    @Override
    public void EditEvent(LocalEventInputData eventInputData) {
        try {
            LocalEvent event = new LocalEvent(eventInputData.getID(), eventInputData.getTitle(), eventInputData.getUserID(),
                    eventInputData.getDate(), eventInputData.getStartTime(), eventInputData.getEndTime(), eventInputData.getLocation(),
                    eventInputData.getDescription(), eventInputData.getIsWork(), eventInputData.getPinned(), eventInputData.getSubEvents());

            eventsDataAccessObject.delete(event.getID());

            eventsDataAccessObject.save(event);

            localEventOutputBoundary.UpdateEventsList(
                    new LocalEventOutputData(event.getID(),
                            event.getTitle(), event.getUserID(),
                            event.getDate(), event.getStartTime(),
                            event.getEndTime(), event.getLocation(),
                            event.getDescription(), event.isWork(),
                            event.getPinned(), event.getDescendants()));
        }

        catch (RuntimeException e) {
            localEventOutputBoundary.EventFailView(
                    "Event editing failed.");
        }
    }

    /**
     * Deletes an event from the database
     * @param eventID The ID of the event to be deleted
     */
    @Override
    public void DeleteEvent(int eventID) {
        try {
            eventsDataAccessObject.delete(eventID);

            localEventOutputBoundary.DeleteEventSuccessView(eventID);
        }

        catch (RuntimeException e) {
            localEventOutputBoundary.EventFailView(
                    "Event deletion failed.");
        }
    }

    /**
     * Displays all events of a user
     * @param userID The ID of the user whose events are to be displayed
     */
    @Override
    public void GetAllEvents(int userID) {
        ArrayList<LocalEventOutputData> eventOutputDataArrayList = new ArrayList<>();

        for (LocalEvent event :
                eventsDataAccessObject.getAllUserEntries(userID)) {
            eventOutputDataArrayList.add(new LocalEventOutputData(event.getID(),
                    event.getTitle(), event.getUserID(), event.getDate(),
                    event.getStartTime(), event.getEndTime(),
                    event.getLocation(), event.getDescription(),
                    event.isWork(), event.getPinned(),
                    event.getDescendants()));
        }

        localEventOutputBoundary.DisplayAllEvents(eventOutputDataArrayList);
    }
}

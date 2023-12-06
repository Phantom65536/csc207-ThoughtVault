package use_case.local_event;

import data_access.EventsDataAccessObject;

import entity.LocalEvent;

import java.util.ArrayList;

public class LocalEventInteractor implements LocalEventInputBoundary {
    private final LocalEventOutputBoundary localEventOutputBoundary;

    private final EventsDataAccessObject eventsDataAccessObject;

    public LocalEventInteractor(EventsDataAccessObject eventsDataAccessObject,
                                LocalEventOutputBoundary
                                        localEventOutputBoundary){
        this.eventsDataAccessObject = eventsDataAccessObject;

        this.localEventOutputBoundary = localEventOutputBoundary;
    }

    @Override
    public void DisplayEventCreationView() {
        localEventOutputBoundary.DisplayEventCreationView();
    }

    @Override
    public void DisplayEventEditView(int eventID) {
        LocalEvent event = eventsDataAccessObject.getByID(eventID);

        localEventOutputBoundary.DisplayEventEditView(new
                LocalEventOutputData(event.getID(), event.getTitle(),
                event.getUserID(), event.getDate(), event.getStartTime(),
                event.getEndTime(), event.getLocation(), event.getDescription(),
                event.isWork(), event.getPinned(), event.getDescendants()));
    }

    @Override
    public void DisplayEventDetailedView(int eventID) {
        LocalEvent event = eventsDataAccessObject.getByID(eventID);

        localEventOutputBoundary.DisplayEventDetailedView(new
                LocalEventOutputData(event.getID(), event.getTitle(),
                event.getUserID(), event.getDate(), event.getStartTime(),
                event.getEndTime(), event.getLocation(), event.getDescription(),
                event.isWork(), event.getPinned(), event.getDescendants()));
    }

    @Override
    public void CreateEvent(EventInputData eventInputData) {
        try {
            LocalEvent event = new LocalEvent(eventInputData.getID(),
                    eventInputData.getTitle(), eventInputData.getUserID(),
                    eventInputData.getDate(), eventInputData.getStartTime(),
                    eventInputData.getEndTime(), eventInputData.getLocation(),
                    eventInputData.getDescription(), eventInputData.getIsWork(),
                    eventInputData.getPinned(), eventInputData.getSubEvents());

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

    @Override
    public void EditEvent(EventInputData eventInputData) {
        try {
            LocalEvent event = new LocalEvent(eventInputData.getID(),
                    eventInputData.getTitle(), eventInputData.getUserID(),
                    eventInputData.getDate(), eventInputData.getStartTime(),
                    eventInputData.getEndTime(), eventInputData.getLocation(),
                    eventInputData.getDescription(), eventInputData.getIsWork(),
                    eventInputData.getPinned(), eventInputData.getSubEvents());

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

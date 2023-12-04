package use_case;

import InputData.EventInputData;

import OutputData.EventOutputData;

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
    public void createEvent(EventInputData eventInputData) {
        try {
            LocalEvent event = new LocalEvent(eventInputData.getID(),
                    eventInputData.getTitle(), eventInputData.getUserID(),
                    eventInputData.getDate(), eventInputData.getStartTime(),
                    eventInputData.getEndTime(), eventInputData.getLocation(),
                    eventInputData.getDescription(), eventInputData.getIsWork(),
                    eventInputData.getPinned(), eventInputData.getSubEvents());

            eventsDataAccessObject.save(event);

            localEventOutputBoundary.CreateEventSuccessView(
                    eventInputData.getTitle());
        }

        catch (RuntimeException e) {
            localEventOutputBoundary.CreateEventFailView(
                    eventInputData.getTitle());
        }
    }

    @Override
    public void editEvent(EventInputData eventInputData) {
        try {
            LocalEvent event = new LocalEvent(eventInputData.getID(),
                    eventInputData.getTitle(), eventInputData.getUserID(),
                    eventInputData.getDate(), eventInputData.getStartTime(),
                    eventInputData.getEndTime(), eventInputData.getLocation(),
                    eventInputData.getDescription(), eventInputData.getIsWork(),
                    eventInputData.getPinned(), eventInputData.getSubEvents());

            eventsDataAccessObject.delete(event.getID());

            eventsDataAccessObject.save(event);

            localEventOutputBoundary.EditEventSuccessView(
                    eventInputData.getTitle());
        }

        catch (RuntimeException e) {
            localEventOutputBoundary.EditEventFailView(
                    eventInputData.getTitle());
        }
    }

    @Override
    public void deleteEvent(EventInputData eventInputData) {
        try {
            eventsDataAccessObject.delete(eventInputData.getID());

            localEventOutputBoundary.DeleteEventSuccessView();
        }

        catch (RuntimeException e) {
            localEventOutputBoundary.DeleteEventFailView();
        }
    }

    @Override
    public void getEvent(int eventID) {
        LocalEvent event = eventsDataAccessObject.getByID(eventID);

        localEventOutputBoundary.DisplayEvent(new EventOutputData(event.getID(),
                event.getTitle(), event.getUserID(), event.getDate(),
                event.getStartTime(), event.getEndTime(), event.getLocation(),
                event.getDescription(), event.isWork(), event.getPinned(),
                event.getDescendants()));
    }

    @Override
    public void getAllEvents(int userID) {
        ArrayList<EventOutputData> eventOutputDataArrayList = new ArrayList<>();

        for (LocalEvent event :
                eventsDataAccessObject.getAllUserEntries(userID)) {
            eventOutputDataArrayList.add(new EventOutputData(event.getID(),
                    event.getTitle(), event.getUserID(), event.getDate(),
                    event.getStartTime(), event.getEndTime(),
                    event.getLocation(), event.getDescription(),
                    event.isWork(), event.getPinned(),
                    event.getDescendants()));
        }

        localEventOutputBoundary.DisplayAllEvents(eventOutputDataArrayList);
    }
}

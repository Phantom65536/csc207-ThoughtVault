package interface_adapter.localEvent;

import use_case.localEvent.LocalEventInputBoundary;
import use_case.localEvent.LocalEventInputData;

import java.time.LocalDate;

import java.time.LocalTime;

import java.util.ArrayList;

public class LocalEventController {
    private final LocalEventInputBoundary localEventInteractor;

    public LocalEventController(LocalEventInputBoundary localEventInteractor) {
        this.localEventInteractor = localEventInteractor;
    }

    public void createEvent(int id, String title, int userID, LocalDate date,
                            LocalTime startTime, LocalTime endTime,
                            String location, String description, boolean isWork,
                            boolean pinned, ArrayList<Integer> subEvents) {
        LocalEventInputData eventInputData = new LocalEventInputData(id, title, userID,
                date, startTime, endTime, location, description, isWork, pinned,
                subEvents);

        localEventInteractor.CreateEvent(eventInputData);
    }

    public void editEvent(int id, String title, int userID, LocalDate date,
                          LocalTime startTime, LocalTime endTime,
                          String location, String description, boolean isWork,
                          boolean pinned, ArrayList<Integer> subEvents) {
        LocalEventInputData eventInputData = new LocalEventInputData(id, title, userID,
                date, startTime, endTime, location, description, isWork, pinned,
                subEvents);

        localEventInteractor.EditEvent(eventInputData);
    }

    public void deleteEvent(int eventID) {
        localEventInteractor.DeleteEvent(eventID);
    }
    public void displayEventDetailedView(int eventID){localEventInteractor.DisplayEventDetailedView(eventID);}


}

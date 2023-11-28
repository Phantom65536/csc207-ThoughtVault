package use_case;


import InputData.EventInputData;
import OutputData.EventOutputData;

import java.time.LocalDate;
import java.util.ArrayList;

public interface LocalEventInputBoundary {
    public void createEvent(EventInputData eventInputData);
    //create and save an event (make sure no sub-events are the same)
    public void editEvent(EventInputData eventInputData); //edit an existing event and save its changes (make sure no sub-events are the same)
    public void deleteEvent(EventInputData eventInputData); //delete an existing event
    public EventOutputData getEvent(String eventID, String userID); //check whether the event belongs to the user and return its corresponding Event instance when the user wants to view its details
    public ArrayList<EventOutputData> getAllEvents(String userID);
}

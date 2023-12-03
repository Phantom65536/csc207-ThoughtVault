package use_case;


import InputData.EventInputData;

public interface LocalEventInputBoundary {
    void createEvent(EventInputData eventInputData);

    //create and save an event (make sure no sub-events are the same)
    void editEvent(EventInputData eventInputData); //edit an existing event and save its changes (make sure no sub-events are the same)

    void deleteEvent(EventInputData eventInputData); //delete an existing event

    void getEvent(int eventID); //check whether the event belongs to the user and return its corresponding Event instance when the user wants to view its details

    void getAllEvents(int userID);
}

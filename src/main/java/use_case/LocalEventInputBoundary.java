package use_case;


public interface LocalEventInputBoundary {
    public void createEvent();
    //create and save an event (make sure no sub-events are the same)
    public void editEvent(); //edit an existing event and save its changes (make sure no sub-events are the same)
    public void deleteEvent(); //delete an existing event
    public void getEvent(); //check whether the event belongs to the user and return its corresponding Event instance when the user wants to view its details
}

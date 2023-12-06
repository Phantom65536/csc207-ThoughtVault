package use_case.local_event;


public interface LocalEventInputBoundary {
    void DisplayEventCreationView();

    void DisplayEventEditView(int eventID);

    void CreateEvent(EventInputData eventInputData);

    void EditEvent(EventInputData eventInputData);

    void DeleteEvent(int eventID);

    void DisplayEventDetailedView(int eventID);

    void GetAllEvents(int userID);
}

package use_case.localEvent;


public interface LocalEventInputBoundary {
    void DisplayEventCreationView();

    void DisplayEventEditView(int eventID);

    void CreateEvent(LocalEventInputData eventInputData);

    void EditEvent(LocalEventInputData eventInputData);

    void DeleteEvent(int eventID);

    void DisplayEventDetailedView(int eventID);

    void GetAllEvents(int userID);
}

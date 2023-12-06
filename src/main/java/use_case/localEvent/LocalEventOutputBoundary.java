package use_case.localEvent;

import java.util.ArrayList;

public interface LocalEventOutputBoundary {
    void UpdateEventsList(LocalEventOutputData localEventOutputData);

    void DeleteEventSuccessView(int ID);

    void EventFailView(String errorMessage);

    void DisplayEventDetailedView(LocalEventOutputData eventOutputData);

    void DisplayEventCreationView();

    void DisplayEventEditView(LocalEventOutputData eventOutputData);

    void DisplayAllEvents(ArrayList<LocalEventOutputData> eventOutputDataArrayList);
}

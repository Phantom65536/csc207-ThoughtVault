package use_case;

import OutputData.EventOutputData;

import java.util.ArrayList;

public interface LocalEventOutputBoundary {
    void CreateEventSuccessView(String title);

    void CreateEventFailView(String title);

    void EditEventSuccessView(String title);

    void EditEventFailView(String title);

    void DeleteEventSuccessView();

    void DeleteEventFailView();

    void DisplayEvent(EventOutputData eventOutputData);

    void DisplayAllEvents(ArrayList<EventOutputData> eventOutputDataArrayList);
}

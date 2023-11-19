package use_case;

import entity.LocalEvent;

import java.util.ArrayList;

public interface EventsDataAccessInterface {
    LocalEvent getByID(int eventID);
    ArrayList<LocalEvent> getAllUserEvents(int userID);
    void save(LocalEvent event);
    void delete(int eventID);
}

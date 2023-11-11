package use_case;

import entity.Notifications;

public interface NotificationsDataAccessInterface {
    Notifications getByID(int eventID);
    void save(Notifications notif);
    void delete(int eventID);
}

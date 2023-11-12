package data_access;

import entity.Notifications;

public interface NotificationsDataAccessInterface {
    Notifications getByID(int eventID);
    void save(Notifications notif);
    void delete(int eventID);
}

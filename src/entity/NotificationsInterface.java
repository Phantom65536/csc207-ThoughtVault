package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface NotificationsInterface {
    ArrayList<LocalDateTime> getOccurrences();
    int getEventID();
    void setOccurrences(ArrayList<LocalDateTime> occurrences);
}

package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Notifications implements NotificationsInterface{
    private final int eventID;
    private ArrayList<LocalDateTime> occurrences;

    public Notifications(int eventID, ArrayList<LocalDateTime> occurrences) {
        this.eventID = eventID;
        this.occurrences = new ArrayList<>(occurrences);
    }

    @Override
    public ArrayList<LocalDateTime> getOccurrences() {
        return occurrences;
    }

    public int getEventID() { return eventID; }

    @Override
    public void setOccurrences(ArrayList<LocalDateTime> occurrences) {
        this.occurrences = occurrences;
    }
}

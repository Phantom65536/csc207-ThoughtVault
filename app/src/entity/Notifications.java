package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Notifications implements NotificationsInterface{
    private final LocalEventInterface event;
    private ArrayList<LocalDateTime> occurrences;

    public Notifications(LocalEventInterface event, ArrayList<LocalDateTime> occurrences) {
        this.event = event;
        this.occurrences = occurrences;
    }

    @Override
    public ArrayList<LocalDateTime> getOccurrences() {
        return occurrences;
    }

    @Override
    public void setOccurrences(ArrayList<LocalDateTime> occurrences) {
        this.occurrences = occurrences;
    }
}

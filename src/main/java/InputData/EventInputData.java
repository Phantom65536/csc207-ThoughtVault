package InputData;

import java.time.LocalDate;

import java.time.LocalTime;

import java.util.ArrayList;

public class EventInputData extends NoteInputData {
    private final LocalDate date;
    // default startTime when user specifies whole-day event

    private LocalTime startTime = LocalTime.MIDNIGHT;

    private LocalTime endTime = LocalTime.of(23, 59);

    public EventInputData (int ID, String title, int userID, LocalDate date,
                           LocalTime startTime, LocalTime endTime,
                           String location, String description, boolean isWork,
                           boolean pinned, ArrayList<Integer> subEvents) {
        super(ID, title, userID, location, description, isWork, pinned,
                subEvents);

        this.date = date;

        this.startTime = startTime;

        this.endTime = endTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }
}

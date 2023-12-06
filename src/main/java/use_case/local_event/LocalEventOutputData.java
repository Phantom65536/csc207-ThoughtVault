package use_case.local_event;

import java.time.LocalDate;

import java.time.LocalTime;

import java.util.ArrayList;
import java.util.HashMap;

public class LocalEventOutputData extends NoteOutputData {
    private final LocalDate date;
    // default startTime when user specifies whole-day event

    private LocalTime startTime = LocalTime.MIDNIGHT;

    private LocalTime endTime = LocalTime.of(23, 59);

    public LocalEventOutputData(int ID, String title, int userID, LocalDate date,
                                LocalTime startTime, LocalTime endTime,
                                String location, String description, boolean isWork,
                                boolean pinned, ArrayList<Integer> subEvents) {
        super(ID, title, userID, location, description, isWork, pinned,
                subEvents);

        this.date = date;

        this.startTime = startTime;

        this.endTime = endTime;
    }

    public HashMap<String, Object> getEventData() {
        HashMap<String, Object> data = super.getNoteData();
        data.put("date", getDate());
        data.put("startTime", getStartTime());
        data.put("endTime", getEndTime());
        return data;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}

package interface_adapter;

import java.time.LocalDate;

import java.time.LocalTime;

import java.util.ArrayList;

public class LocalEventState extends NoteState {
    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    public LocalEventState() {
    }

    public LocalEventState(String title, String location, String description,
                           boolean isWork, boolean pinned,
                           ArrayList<Integer> subEvents, LocalDate date,
                           LocalTime startTime, LocalTime endTime) {
        super(title, location, description, isWork, pinned, subEvents);

        this.date = date;

        this.startTime = startTime;

        this.endTime = endTime;
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

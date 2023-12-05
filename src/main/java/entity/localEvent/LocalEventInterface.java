package entity.localEvent;

import entity.note.NoteInterface;

import java.time.LocalDate;

import java.time.LocalTime;

public interface LocalEventInterface extends NoteInterface {
    LocalDate getDate();

    LocalTime getStartTime();

    LocalTime getEndTime();
}

package entity;

import entity.note.Note;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public interface NoteFactoryInterface {
    public Note createNote(int ID, String title, int userID, String location,
                           String description, boolean isWork,
                           boolean pinned, ArrayList<Integer> descendants);

    public Note createNote(int ID, String title, int userID, LocalDate date,
                           LocalTime startTime, LocalTime endTime,
                           String location, String description, boolean isWork,
                           boolean pinned, ArrayList<Integer> descendants);
}

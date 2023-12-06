package entity.note;

import entity.NoteFactoryInterface;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class NoteFactory implements NoteFactoryInterface {
    @Override
    public Note createNote(int ID, String title, int userID, String location,
                           String description, boolean isWork, boolean pinned,
                           ArrayList<Integer> descendants) {
        return new Note(ID, title, userID, location, description, isWork,
                pinned, descendants);
    }

    @Override
    public Note createNote(int ID, String title, int userID, LocalDate date,
                           LocalTime startTime, LocalTime endTime,
                           String location, String description, boolean isWork,
                           boolean pinned, ArrayList<Integer> descendants) {
        return null;
    }
}

package entity.localEvent;

import entity.NoteFactoryInterface;
import entity.note.Note;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class LocalEventFactory implements NoteFactoryInterface {
    @Override
    public LocalEvent createNote(int ID, String title, int userID,
                                 String location, String description,
                                 boolean isWork, boolean pinned,
                                 ArrayList<Integer> descendants) {
        return null;
    }

    @Override
    public LocalEvent createNote(int ID, String title, int userID,
                                 LocalDate date, LocalTime startTime,
                                 LocalTime endTime, String location,
                                 String description, boolean isWork,
                                 boolean pinned, ArrayList<Integer>
                                         descendants) {
        return new LocalEvent(ID, title, userID, date, startTime, endTime,
                location, description, isWork, pinned, descendants);
    }
}

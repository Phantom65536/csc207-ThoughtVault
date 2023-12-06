package use_case.local_event;

import use_case.local_event.NoteInputData;
import use_case.local_event.NoteInputBoundary;

import java.util.ArrayList;

public class NoteController {
    private final NoteInputBoundary noteInteractor;

    public NoteController(NoteInputBoundary noteInteractor) {
        this.noteInteractor = noteInteractor;
    }

    public void createNote(int id, String title, int userID, String location,
                           String description, boolean isWork, boolean pinned,
                           ArrayList<Integer> subEvents) {
        NoteInputData noteInputData = new NoteInputData(id, title, userID,
                location, description, isWork, pinned, subEvents);

        noteInteractor.CreateNote(noteInputData);
    }

    public void editNote(int id, String title, int userID, String location,
                         String description, boolean isWork, boolean pinned,
                         ArrayList<Integer> subEvents) {
        NoteInputData noteInputData = new NoteInputData(id, title, userID,
                location, description, isWork, pinned, subEvents);

        noteInteractor.EditNote(noteInputData);
    }

    public void deleteNote(int NoteID) {
        noteInteractor.DeleteNote(NoteID);
    }
    public void displayNoteDetailedView(int noteId) {noteInteractor.DisplayNoteDetailedView(noteId);
    }
}

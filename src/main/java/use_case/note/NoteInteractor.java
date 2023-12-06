package use_case.note;

import data_access.NotesDataAccessObject;

import entity.note.Note;

import java.util.ArrayList;

public class NoteInteractor implements NoteInputBoundary {
    private final NoteOutputBoundary noteOutputBoundary;

    private final NotesDataAccessObject notesDataAccessObject;

    public NoteInteractor(NoteOutputBoundary noteOutputBoundary,
                          NotesDataAccessObject notesDataAccessObject){
        this.noteOutputBoundary = noteOutputBoundary;

        this.notesDataAccessObject = notesDataAccessObject;
    }

    public void DisplayNoteCreationView() {
        noteOutputBoundary.DisplayNoteCreationView();
    }

    @Override
    public void DisplayNoteEditView(int noteID) {
        Note note = notesDataAccessObject.getByID(noteID);

        noteOutputBoundary.DisplayNoteEditView(new
                NoteOutputData(note.getID(), note.getTitle(), note.getUserID(),
                note.getLocation(), note.getDescription(), note.isWork(),
                note.getPinned(), note.getDescendants()));
    }

    @Override
    public void DisplayNoteDetailedView(int noteID) {
        Note note = notesDataAccessObject.getByID(noteID);

        noteOutputBoundary.DisplayNoteDetailedView(new
                NoteOutputData(note.getID(), note.getTitle(), note.getUserID(),
                note.getLocation(), note.getDescription(), note.isWork(),
                note.getPinned(), note.getDescendants()));
    }

    @Override
    public void CreateNote(NoteInputData noteInputData) {
        try {
            Note note = new Note(notesDataAccessObject.getNewID(), noteInputData.getTitle(),
                    noteInputData.getUserID(), noteInputData.getLocation(),
                    noteInputData.getDescription(),
                    noteInputData.getIsWork(), noteInputData.getPinned(),
                    noteInputData.getSubEvents());

            notesDataAccessObject.save(note);

            noteOutputBoundary.UpdateNotesList(new NoteOutputData(note.getID(),
                    note.getTitle(), note.getUserID(), note.getLocation(),
                    note.getDescription(), note.isWork(), note.getPinned(),
                    note.getDescendants()));
        }

        catch (RuntimeException e) {
            noteOutputBoundary.NoteFailView(
                    "Note creation failed.");
        }
    }

    @Override
    public void EditNote(NoteInputData noteInputData) {
        try {
            Note note = new Note(noteInputData.getID(), noteInputData.getTitle(),
                    noteInputData.getUserID(), noteInputData.getLocation(),
                    noteInputData.getDescription(), noteInputData.getIsWork(),
                    noteInputData.getPinned(), noteInputData.getSubEvents());

            notesDataAccessObject.delete(note.getID());

            notesDataAccessObject.save(note);

            noteOutputBoundary.UpdateNotesList(new NoteOutputData(note.getID(),
                    note.getTitle(), note.getUserID(), note.getLocation(),
                    note.getDescription(), note.isWork(), note.getPinned(),
                    note.getDescendants()));
        }

        catch (RuntimeException e) {
            noteOutputBoundary.NoteFailView(
                    "Note editing failed.");
        }
    }

    @Override
    public void DeleteNote(int noteID) {
        try {
            notesDataAccessObject.delete(noteID);

            noteOutputBoundary.DeleteNoteSuccessView(noteID);
        }

        catch (RuntimeException e) {
            noteOutputBoundary.NoteFailView(
                    "Note deletion failed.");
        }
    }

    @Override
    public void GetAllNotes(int userID) {
        ArrayList<NoteOutputData> noteOutputDataArrayList = new ArrayList<>();

        for (Note note : notesDataAccessObject.getAllUserEntries(userID)) {
            noteOutputDataArrayList.add(new NoteOutputData(note.getID(),
                    note.getTitle(), note.getUserID(), note.getLocation(),
                    note.getDescription(), note.isWork(), note.getPinned(),
                    note.getDescendants()));
        }

        noteOutputBoundary.DisplayAllNotes(noteOutputDataArrayList);
    }
}

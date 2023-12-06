package use_case.note;

import InputData.NoteInputData;

import data_access.NotesDataAccessObject;

import entity.NoteFactoryInterface;
import entity.note.Note;
import entity.note.NoteFactory;

import java.util.ArrayList;

public class NoteInteractor implements NoteInputBoundary {
    private final NoteOutputBoundary noteOutputBoundary;

    private final NotesDataAccessObject<Note> notesDataAccessObject;

    private final NoteFactoryInterface noteFactory;

    public NoteInteractor(NoteOutputBoundary noteOutputBoundary,
                          NotesDataAccessObject<Note> notesDataAccessObject){
        this.noteOutputBoundary = noteOutputBoundary;

        this.notesDataAccessObject = notesDataAccessObject;

        this.noteFactory = new NoteFactory();
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
            Note note = noteFactory.createNote(noteInputData.getID(),
                    noteInputData.getTitle(), noteInputData.getUserID(),
                    noteInputData.getLocation(), noteInputData.getDescription(),
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
            Note note = noteFactory.createNote(noteInputData.getID(),
                    noteInputData.getTitle(), noteInputData.getUserID(),
                    noteInputData.getLocation(), noteInputData.getDescription(),
                    noteInputData.getIsWork(), noteInputData.getPinned(),
                    noteInputData.getSubEvents());

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
package use_case;

import InputData.NoteInputData;

import OutputData.NoteOutputData;

import data_access.NotesDataAccessObject;

import entity.Note;

import java.util.ArrayList;

public class NoteInteractor implements NoteInputBoundary {
    private final NoteOutputBoundary noteOutputBoundary;

    private final NotesDataAccessObject<Note> notesDataAccessObject;

    public NoteInteractor(NoteOutputBoundary noteOutputBoundary,
                          NotesDataAccessObject<Note> notesDataAccessObject){
        this.noteOutputBoundary = noteOutputBoundary;

        this.notesDataAccessObject = notesDataAccessObject;
    }

    @Override
    public void createNote(NoteInputData NoteInputData) {
        try {
            Note note = new Note(NoteInputData.getID(), NoteInputData.getTitle(),
                    NoteInputData.getUserID(), NoteInputData.getLocation(),
                    NoteInputData.getDescription(),
                    NoteInputData.getIsWork(), NoteInputData.getPinned(),
                    NoteInputData.getSubEvents());

            notesDataAccessObject.save(note);

            noteOutputBoundary.CreateNoteSuccessView(NoteInputData.getTitle());
        }

        catch (RuntimeException e) {
            noteOutputBoundary.CreateNoteFailView(NoteInputData.getTitle());
        }
    }

    @Override
    public void editNote(NoteInputData NoteInputData) {
        try {
            Note note = new Note(NoteInputData.getID(), NoteInputData.getTitle(),
                    NoteInputData.getUserID(), NoteInputData.getLocation(),
                    NoteInputData.getDescription(), NoteInputData.getIsWork(),
                    NoteInputData.getPinned(), NoteInputData.getSubEvents());

            notesDataAccessObject.delete(note.getID());

            notesDataAccessObject.save(note);

            noteOutputBoundary.EditNoteSuccessView(NoteInputData.getTitle());
        }

        catch (RuntimeException e) {
            noteOutputBoundary.EditNoteFailView(NoteInputData.getTitle());
        }
    }

    @Override
    public void deleteNote(int NoteID) {
        try {
            notesDataAccessObject.delete(NoteID);

            noteOutputBoundary.DeleteNoteSuccessView();
        }

        catch (RuntimeException e) {
            noteOutputBoundary.DeleteNoteFailView();
        }
    }

    @Override
    public void getNote(int NoteID) {
        Note note = notesDataAccessObject.getByID(NoteID);

        noteOutputBoundary.DisplayNote(new NoteOutputData(note.getID(),
                note.getTitle(), note.getUserID(), note.getLocation(),
                note.getDescription(), note.isWork(), note.getPinned(),
                note.getDescendants()));
    }

    @Override
    public void getAllNotes(int userID) {
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

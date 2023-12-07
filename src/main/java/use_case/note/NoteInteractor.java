package use_case.note;

import data_access.NotesDataAccessObject;

import entity.note.Note;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The interactor for the Note entity
 */
public class NoteInteractor implements NoteInputBoundary {
    private final NoteOutputBoundary noteOutputBoundary;

    private final NotesDataAccessObject notesDataAccessObject;

    /**
     * Constructor for NoteInteractor
     * @param noteOutputBoundary The presenter for the NoteInteractor
     * @param notesDataAccessObject The data access object for the NoteInteractor
     */
    public NoteInteractor(NoteOutputBoundary noteOutputBoundary,
                          NotesDataAccessObject notesDataAccessObject){
        this.noteOutputBoundary = noteOutputBoundary;

        this.notesDataAccessObject = notesDataAccessObject;
    }

    /**
     * Displays the note detailed view
     * @param noteID The ID of the note to be displayed
     */
    @Override
    public void DisplayNoteDetailedView(int noteID) {
        Note note = notesDataAccessObject.getByID(noteID);

        noteOutputBoundary.DisplayNoteDetailedView(new
                NoteOutputData(note.getID(), note.getTitle(), note.getUserID(),
                note.getLocation(), note.getDescription(), note.isWork(),
                note.getPinned(), note.getDescendants(),
                (HashMap<Integer, String>) notesDataAccessObject.getTitlesOfAllEntries(note.getUserID())
                )
        );
    }

    /**
     * Creates a note and saves it to the database
     * @param noteInputData The input data for the note to be created
     */
    @Override
    public void CreateNote(NoteInputData noteInputData) {
        try {
            Note note = new Note(notesDataAccessObject.getNewID(), noteInputData.getTitle(), noteInputData.getUserID(),
                    noteInputData.getLocation(), noteInputData.getDescription(), noteInputData.getIsWork(), noteInputData.getPinned(),
                    noteInputData.getSubEntries());

            notesDataAccessObject.save(note);

            noteOutputBoundary.UpdateNotesList(
                    new NoteOutputData(note.getID(),
                    note.getTitle(), note.getUserID(), note.getLocation(),
                    note.getDescription(), note.isWork(), note.getPinned(),
                    note.getDescendants(), (HashMap<Integer, String>) notesDataAccessObject.getTitlesOfAllEntries(note.getUserID())
                    )
            );
        }

        catch (RuntimeException e) {
            noteOutputBoundary.NoteFailView(
                    "Note creation failed.");
        }
    }

    /**
     * Edits a note and saves it to the database
     * @param noteInputData The input data for the note to be edited
     */
    @Override
    public void EditNote(NoteInputData noteInputData) {
        try {
            Note note = new Note(noteInputData.getID(), noteInputData.getTitle(), noteInputData.getUserID(),
                    noteInputData.getLocation(), noteInputData.getDescription(), noteInputData.getIsWork(), noteInputData.getPinned(),
                    noteInputData.getSubEntries());

            notesDataAccessObject.delete(note.getID());

            notesDataAccessObject.save(note);

            noteOutputBoundary.UpdateNotesList(
                    new NoteOutputData(note.getID(),
                    note.getTitle(), note.getUserID(), note.getLocation(),
                    note.getDescription(), note.isWork(), note.getPinned(),
                    note.getDescendants(), (HashMap<Integer, String>) notesDataAccessObject.getTitlesOfAllEntries(note.getUserID())
                    )
            );
        }

        catch (RuntimeException e) {
            noteOutputBoundary.NoteFailView(
                    "Note editing failed.");
        }
    }

    /**
     * Deletes a note from the database
     * @param noteID The ID of the note to be deleted
     */
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

    /**
     * Displays all notes in the database
     * @param userID The ID of the user whose notes are to be displayed
     */
    @Override
    public void GetAllNotes(int userID) {
        ArrayList<NoteOutputData> noteOutputDataArrayList = new ArrayList<>();

        for (Note note : notesDataAccessObject.getAllUserEntries(userID)) {
            noteOutputDataArrayList.add(
                    new NoteOutputData(note.getID(),
                    note.getTitle(), note.getUserID(), note.getLocation(),
                    note.getDescription(), note.isWork(), note.getPinned(),
                    note.getDescendants(), (HashMap<Integer, String>) notesDataAccessObject.getTitlesOfAllEntries(note.getUserID())
                    )
            );
        }

        noteOutputBoundary.DisplayAllNotes(noteOutputDataArrayList);
    }

    @Override
    public void switchToEdit() {
        noteOutputBoundary.switchToEdit();
    }
}

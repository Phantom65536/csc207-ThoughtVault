package use_case;

import InputData.NoteInputData;

public interface NoteInputBoundary {
    void createNote(NoteInputData noteInputData);

    void editNote(NoteInputData noteInputData);

    void deleteNote(int noteID);

    void getNote(int noteID);

    void getAllNotes(int userID);
}

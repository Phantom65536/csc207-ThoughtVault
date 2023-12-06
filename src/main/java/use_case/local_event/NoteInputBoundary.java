package use_case.local_event;

import use_case.local_event.NoteInputData;

public interface NoteInputBoundary {
    void DisplayNoteCreationView();

    void DisplayNoteEditView(int noteID);

    void DisplayNoteDetailedView(int noteID);

    void CreateNote(NoteInputData noteInputData);

    void EditNote(NoteInputData noteInputData);

    void DeleteNote(int noteID);

    void GetAllNotes(int userID);
}

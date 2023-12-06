package use_case.local_event;

import use_case.local_event.NoteOutputData;

import java.util.ArrayList;

public interface NoteOutputBoundary {
    void UpdateNotesList(NoteOutputData noteOutputData);

    void DeleteNoteSuccessView(int ID);

    void NoteFailView(String errorMessage);

    void DisplayNoteDetailedView(NoteOutputData noteOutputData);

    void DisplayNoteCreationView();

    void DisplayNoteEditView(NoteOutputData noteOutputData);

    void DisplayAllNotes(ArrayList<NoteOutputData> noteOutputDataArrayList);
}

package use_case.note;

import OutputData.NoteOutputData;

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

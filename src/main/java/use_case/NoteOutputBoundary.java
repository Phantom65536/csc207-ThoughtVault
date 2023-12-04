package use_case;

import OutputData.NoteOutputData;

import java.util.ArrayList;

public interface NoteOutputBoundary {
    void CreateNoteSuccessView(String title);

    void CreateNoteFailView(String title);

    void EditNoteSuccessView(String title);

    void EditNoteFailView(String title);

    void DeleteNoteSuccessView();

    void DeleteNoteFailView();

    void DisplayNote(NoteOutputData noteOutputData);

    void DisplayAllNotes(ArrayList<NoteOutputData> noteOutputDataArrayList);
}

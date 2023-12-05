package interface_adapter;

import OutputData.NoteOutputData;

import use_case.NoteOutputBoundary;

import java.util.ArrayList;

import java.util.HashMap;

public class NotePresenter implements NoteOutputBoundary {
    private final NoteViewModel detailedNoteViewModel;

    private final NoteViewModel noteCreationViewModel;

    private final NoteViewModel noteEditViewModel;

    private final ListViewModel listViewModel;

    private final ViewManagerModel viewManagerModel;

    public NotePresenter(ViewManagerModel viewManagerModel,
                         NoteViewModel detailedNoteViewModel,
                         NoteViewModel noteCreationViewModel,
                         NoteViewModel noteEditViewModel,
                         ListViewModel listViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.detailedNoteViewModel = detailedNoteViewModel;
        this.noteCreationViewModel = noteCreationViewModel;
        this.noteEditViewModel = noteEditViewModel;
        this.listViewModel = listViewModel;
    }

    public NoteState CreateState(NoteOutputData noteOutputData) {
        return new NoteState(noteOutputData.getTitle(),
                noteOutputData.getLocation(),
                noteOutputData.getDescription(),
                noteOutputData.getIsWork(),
                noteOutputData.getPinned(),
                noteOutputData.getSubEvents());
    }

    @Override
    public void UpdateNotesList(NoteOutputData
                                         noteOutputData) {
        ListViewState listViewState = listViewModel.getState();
        HashMap<Integer, HashMap<String, ?>> listViewNotes =
                listViewState.getNotes();
        listViewNotes.put(noteOutputData.getID(),
                noteOutputData.getNoteData());
        listViewState.setNotes(listViewNotes);
        listViewModel.firePropertyChanged();
        DisplayNoteDetailedView(noteOutputData);
    }

    @Override
    public void DeleteNoteSuccessView(int ID) {
        ListViewState listViewState = listViewModel.getState();
        HashMap<Integer, HashMap<String, ?>> noteViewModelArrayList =
                listViewState.getNotes();
        noteViewModelArrayList.remove(ID);
        listViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(listViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void NoteFailView(String errorMessage) {
        System.out.println(errorMessage);
    }

    @Override
    public void DisplayNoteDetailedView(NoteOutputData noteOutputData) {
        NoteState noteState = new NoteState(
                noteOutputData.getTitle(),
                noteOutputData.getLocation(),
                noteOutputData.getDescription(),
                noteOutputData.getIsWork(),
                noteOutputData.getPinned(),
                noteOutputData.getSubEvents());
        detailedNoteViewModel.setState(noteState);
        detailedNoteViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(
                detailedNoteViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void DisplayNoteCreationView() {
        NoteState noteState = new NoteState();
        noteCreationViewModel.setState(noteState);
        noteCreationViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(
                noteCreationViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void DisplayNoteEditView(NoteOutputData noteOutputData) {
        NoteState noteState = CreateState(noteOutputData);
        noteEditViewModel.setState(noteState);
        noteEditViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(noteEditViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void DisplayAllNotes(ArrayList<NoteOutputData>
                                         noteOutputDataArrayList) {
        HashMap<Integer, HashMap<String, ?>> listViewNotes = new HashMap<>();
        for (NoteOutputData noteOutputData :
                noteOutputDataArrayList) {
            listViewNotes.put(noteOutputData.getID(),
                    noteOutputData.getNoteData());
        }
        ListViewState listViewState = listViewModel.getState();
        listViewState.setNotes(listViewNotes);
        listViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(listViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}


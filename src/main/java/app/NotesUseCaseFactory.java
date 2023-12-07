package app;

import data_access.EventsDataAccessObject;
import data_access.NotesDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.listView.ListViewModel;
import interface_adapter.note.NoteController;
import interface_adapter.note.NotePresenter;
import interface_adapter.note.NoteViewModel;
import use_case.note.NoteInputBoundary;
import use_case.note.NoteInteractor;
import use_case.note.NoteOutputBoundary;
import view.note.CreateNoteView;
import view.note.DetailedNoteView;
import view.note.EditNoteView;


import javax.swing.*;
import java.io.IOException;
import java.text.ParseException;

public class NotesUseCaseFactory {

    /** Prevent instantiation. */
    private NotesUseCaseFactory() {}

    public static CreateNoteView createNoteView(
            ViewManagerModel viewManagerModel,
            NoteViewModel detailedNoteViewModel,
            NoteViewModel noteCreationViewModel,
            NoteViewModel noteEditViewModel,
            ListViewModel listViewModel) {

        try {
            NoteController noteController = createNoteUseCase(
                    viewManagerModel, detailedNoteViewModel, noteCreationViewModel,
                    noteEditViewModel, listViewModel
            );
            return new CreateNoteView(noteCreationViewModel, viewManagerModel, noteController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        } catch (ParseException | org.json.simple.parser.ParseException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static EditNoteView createEditNoteView(
            ViewManagerModel viewManagerModel,
            NoteViewModel detailedNoteViewModel,
            NoteViewModel noteCreationViewModel,
            NoteViewModel noteEditViewModel,
            ListViewModel listViewModel) {

        try {
            NoteController noteController = createNoteUseCase(
                    viewManagerModel, detailedNoteViewModel, noteCreationViewModel,
                    noteEditViewModel, listViewModel
            );
            return new EditNoteView(noteEditViewModel, viewManagerModel, noteController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        } catch (ParseException | org.json.simple.parser.ParseException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static DetailedNoteView createDetailedLNoteView(
            ViewManagerModel viewManagerModel,
            NoteViewModel detailedNoteViewModel,
            NoteViewModel noteCreationViewModel,
            NoteViewModel noteEditViewModel,
            ListViewModel listViewModel) {

        try {
            NoteController noteController = createNoteUseCase(
                    viewManagerModel, detailedNoteViewModel, noteCreationViewModel,
                    noteEditViewModel, listViewModel
            );
            return new DetailedNoteView(detailedNoteViewModel, viewManagerModel,noteController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        } catch (ParseException | org.json.simple.parser.ParseException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private static NoteController createNoteUseCase(
            ViewManagerModel viewManagerModel,
            NoteViewModel detailedNoteViewModel,
            NoteViewModel noteCreationViewModel,
            NoteViewModel noteEditViewModel,
            ListViewModel listViewModel
    ) throws IOException, ParseException, org.json.simple.parser.ParseException {

        // Notice how we pass this method's parameters to the Presenter.
        NoteOutputBoundary noteOutputBoundary = new NotePresenter(
                viewManagerModel, detailedNoteViewModel, noteCreationViewModel,
                noteEditViewModel, listViewModel
        );

        NotesDataAccessObject notesDataAccessObject = new NotesDataAccessObject("./testEvents.json");

        NoteInputBoundary noteInteractor = new NoteInteractor(
                noteOutputBoundary, notesDataAccessObject
        );

        return new NoteController(noteInteractor);
    }
}

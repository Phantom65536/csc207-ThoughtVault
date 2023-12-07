package view.note;

import data_access.EventsDataAccessObject;
import data_access.NotesDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.listView.ListViewModel;
import interface_adapter.localEvent.LocalEventController;
import interface_adapter.localEvent.LocalEventPresenter;
import interface_adapter.localEvent.LocalEventViewModel;
import interface_adapter.note.NoteController;
import interface_adapter.note.NotePresenter;
import interface_adapter.note.NoteViewModel;
import junit.framework.TestCase;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import use_case.localEvent.LocalEventInputBoundary;
import use_case.localEvent.LocalEventInteractor;
import use_case.localEvent.LocalEventOutputBoundary;
import use_case.note.NoteInputBoundary;
import use_case.note.NoteInteractor;
import use_case.note.NoteOutputBoundary;
import view.localEvent.DetailedLocalEventView;

import javax.swing.*;

import java.io.IOException;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class DetailedNoteViewTest {
    @Test
    public void testDetailedNoteView() throws IOException, ParseException {
        NotesDataAccessObject notesDataAccessObject = new NotesDataAccessObject("./testEvents.json");
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        NoteViewModel noteViewModel = new NoteViewModel("detailed note view");
        NoteViewModel noteCreationViewModel = new NoteViewModel("create note view");
        NoteViewModel noteEditViewModel = new NoteViewModel("edit note view");
        ListViewModel listViewModel = new ListViewModel();
        NotePresenter notePresenter = new NotePresenter(
                viewManagerModel, noteViewModel, noteCreationViewModel, noteEditViewModel, listViewModel
        );
        NoteInteractor sib = new NoteInteractor(notePresenter, notesDataAccessObject);
        NoteController controller = new NoteController(sib);

        JPanel detailedLocalEventView = new DetailedNoteView(noteViewModel, viewManagerModel, controller);
        JFrame jf = new JFrame();
        jf.setContentPane(detailedLocalEventView);
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setSize(400,400);
        jf.show();

        try {
            sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
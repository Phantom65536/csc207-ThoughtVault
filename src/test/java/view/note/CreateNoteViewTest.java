package view.note;

import data_access.NotesDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.listView.ListViewModel;
import interface_adapter.note.NoteController;
import interface_adapter.note.NotePresenter;
import interface_adapter.note.NoteViewModel;
import junit.framework.TestCase;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import use_case.note.NoteInteractor;

import javax.swing.*;
import java.io.IOException;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class CreateNoteViewTest {
    @Test
    public void testCreateNoteView() throws IOException, ParseException {
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

        JPanel createNoteView = new CreateNoteView(noteViewModel, viewManagerModel, controller);
        JFrame jf = new JFrame();
        jf.setContentPane(createNoteView);
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
package app;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.json.simple.parser.ParseException;

import data_access.EventsDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.exportevents.ExportEventsViewModel;
import interface_adapter.importevents.ImportEventsViewModel;
import interface_adapter.listView.ListViewModel;
import interface_adapter.localEvent.LocalEventController;
import interface_adapter.localEvent.LocalEventViewModel;
import interface_adapter.note.NoteController;
import interface_adapter.note.NoteViewModel;
import view.ListView;

public class HomePageUseCaseFactory {
    private HomePageUseCaseFactory(){}
    public static ListView create(
                    ListViewModel listViewModel,
                    ImportEventsViewModel importEventsViewModel,
                    ExportEventsViewModel exportEventsViewModel,
                    ViewManagerModel viewManagerModel,
                    LocalEventViewModel localEventViewModel,
                    NoteViewModel noteViewModel,
                    LocalEventController localEventController, 
                    NoteController noteController) {
        
                        return new ListView(listViewModel, importEventsViewModel, exportEventsViewModel, viewManagerModel, localEventViewModel, noteViewModel, localEventController, noteController);
    }
}
